import time
from asyncio.coroutines import iscoroutinefunction


def log_time(func):
    """打印方法执行时间"""

    def fun(*args, **kwargs):
        t = time.perf_counter()
        result = func(*args, **kwargs)
        print(f'func {func.__name__} cost time:{time.perf_counter() - t:.8f} s')
        return result

    async def fun_async(*args, **kwargs):
        t = time.perf_counter()
        result = await func(*args, **kwargs)
        print(f'func {func.__name__} cost time:{time.perf_counter() - t:.8f} s')
        return result

    if iscoroutinefunction(func):
        return fun_async
    return fun
