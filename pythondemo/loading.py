"""测试simpy"""

import collections

product_name = 'changer1'

Product = collections.namedtuple('Product', ['name', 'steps'])  # 产品名称和工序列表
Step = collections.namedtuple('Step', ['step_name', 'cycle_time', 'workstation'])  # 产品某个工序的名称/CT时间/工作站名称
StepResult = collections.namedtuple('StepResult', ['step_name', 'input', 'output'])  # 某一工序的输入与输出

## 产品的工序列表定义
step_map = collections.defaultdict(list)
step_map[product_name].append(Step('step1', 1, 'wks1'))
step_map[product_name].append(Step('step2', 1, 'wks2'))
step_map[product_name].append(Step('step3', 1, 'wks3'))


p1 = Product(product_name, step_map[product_name])

## 机台数量定义
wks_map = collections.defaultdict()
wks_map['wks1'] = 10
wks_map['wks2'] = 10
wks_map['wks3'] = 10


# print(wks_map)
p1_loading = [10, 10, 10, 10, 10, 10]


def first_step_loading_in_day(day: int, first_step_loading: list) -> int:
    """计算每日的初始loading数量"""
    day_index = day - 1
    if day_index >= len(first_step_loading):
        return 0
    return first_step_loading[day_index]


def day_res(day: int, result) -> list[StepResult] | None:
    """返回某一天的输入和输出"""
    if day <= 0:
        return None
    return result['day_' + str(day)]


def step_left(step_index: int, result):
    """计算某个step的剩余未处理的量是多少"""
    input_sum = 0
    output_sum = 0
    for value in result.values():
        input_sum += value[step_index - 1].input
        output_sum += value[step_index - 1].output
    return input_sum - output_sum


def cal_current_day_output(step_index: int, input_loading: int, wfs_1day: int, result):
    """计算当前天的output"""
    pre_wait_num = step_left(step_index, result)  # 计算当前步骤,截至到上一天的，剩余未处置的产品数量是多少
    current_wait_num = pre_wait_num + input_loading
    if current_wait_num == 0:
        return 0
    elif current_wait_num >= wfs_1day:
        return wfs_1day
    else:
        return current_wait_num


def cal_pre_day_pre_step_output(current_day: int, step_index: int, io_result):
    """查询上一天上一步骤的output"""
    if current_day == 1:
        return 0
    previous_day_list = day_res(current_day - 1, io_result)  # 上一天的result
    if previous_day_list is None:
        return 0
    return previous_day_list[step_index - 2].output


def cal():
    io_result = collections.defaultdict(list[StepResult])
    # result_list = []
    ## 下一次的输入依赖上一次的输入
    current_day = 0
    while True:
        current_day_list = []  # 当前天的result
        current_day += 1
        current_day_name = 'day_' + str(current_day)

        step_index = 0
        for step in p1.steps:
            step_index += 1

            step_name = step.step_name
            ct = step.cycle_time
            wks = step.workstation

            wfs_pre_day = 1 / ct  # 通过CT计算出一天能够处理的产品数目
            installed_tool = wks_map[wks]  # 获取wks下安装的机器数目
            wfs_1day = installed_tool * wfs_pre_day  # 当天能够处理的产品数目是多少，作为当前天的输出；下一天会获取当前天的输出作为输入

            input_loading = 0
            output = 0
            if step_index == 1:
                # print(step_name, "wait_num", wait_num)
                input_loading = first_step_loading_in_day(current_day, p1_loading)
            else:
                input_loading = cal_pre_day_pre_step_output(current_day, step_index, io_result)  # 上一天，上一步骤的output

            output = cal_current_day_output(step_index, input_loading, wfs_1day, io_result)
            current_day_list.append(StepResult(step_name, input_loading, output))

        io_result[current_day_name] = current_day_list
        # result_list.append(current_day_list)
        if current_day == 40:
            """一共计算十天的量"""
            break

    for key, value in io_result.items():
        print(key, ':', value)

    ## 需要先初始化第一天的数据，后续再递推?需要么  ## 横向计算输入，上一步的输出，就是下一步的输入  ## 纵向计算单个step 的输出总量，就可以计算出 单个step的完成时间了  ## 按天计算的粒度，还是有点粗了，如果更细致的计算，计算模型是什么  # import numpy as np  # test = np.zeros((2, 3), dtype=np.int_)  #  # print(test)


cal()
