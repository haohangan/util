 /**
     * 根据月份计算季度
     * 除法向上求整
     * 方法1: month / 3 + (month % 3 == 0 ? 0 : 1)
     * 方法2: (int) Math.ceil((double) month / 3)
     * 方法3:  (month + 3 - 1) / 3
     *
     * @param month 月份
     * @return 季度
     */
    private static int quarter(int month) {
        return (month + 3 - 1) / 3;
    }
