import pandas as pd
import numpy as np
from itertools import combinations

import matplotlib.pyplot as plt

# data = {'ID': [101, 102, 103, 104, 105],
#         'Name': ['Alice', 'Bob', 'Charlie', 'David', 'Eva'],
#         'Age': [25, 30, 22, 35, 28],
#         'Salary': [50000, 60000, 45000, 75000, 55000],
#         'Status': ['Active', 'Inactive', 'Active', 'Active', 'Inactive']}
#
# df = pd.DataFrame(data)

# my_list = [1, 2, 3, 4, 5]
# my_array = np.array(my_list)
# my_array = np.array([[1, 2, 3], [4, 5, 6]])
# print(my_array.shape)
# my_array = my_array.reshape(3, 2)
# print(my_array.ndim) 查看维度
# print(my_array.size) 查看大小
# my_array.astype(float) 修改类型
# ar = np.zeros((3, 3)) 零值填充
# ar = np.ones((3, 3))
# np_full = np.full((2, 3), 5)
# empty_array = np.empty((2, 3))
# ar = np.arange(1, 10, 2)  # 步长为2的数组，包含1，不包含10
# linspace_array = np.linspace(1, 10, 5)  # 从1到10，共5个数，等间隔
# # 创建服从均匀分布的随机数组
# rand_array = np.random.rand(2, 3)
# print("均匀分布的随机数组:\n", rand_array)
#
# # 创建服从标准正态分布的随机数组
# randn_array = np.random.randn(2, 3)
# print("标准正态分布的随机数组:\n", randn_array)
#
# # 创建指定范围的随机整数数组
# randint_array = np.random.randint(1, 10, size=(2, 3))
# print("指定范围的随机整数数组:\n", randint_array)
#
# # 创建服从均匀分布的随机数组
# random_array = np.random.random((2, 3))
# print("均匀分布的随机数组:\n", random_array)
# my_array = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
# my_array = np.array([[1, 2, 3], [4, 5, 6]])
# sub_array = my_array[0:2, 1:3]
# print(my_array.flatten()) 变一维数组
# print(my_array.ravel())


"""
pandas操作
"""
# print(df.head(2)) 头两行
# print(df.tail(1)) 尾一行
# print(df.info()) 信息
# print(df.describe()) 统计信息

# print(df['ID'].loc[2]) 选择行 列
# print(df[(df['Age'] > 25) & (df['Status'] == 'Active')]) 条件选择
# print(df.sort_values(by='Salary', ascending=False)) 按工资降序排列
# print(df.dropna()) 删除包含缺失值的行
# print(df.fillna(df.mean())) 用均值填充所有缺失值
# print(df.mean()) 按列均值
# print(df.rename(columns={'ID': 'EmployeeID'}, inplace=True)) 重命名指定列
# print(df.groupby('Status').agg({'Age': 'mean', 'Salary': 'sum'})) 分组后计算年龄均值和总工资 aggregate
# pd.concat([df, df], axis=0) 垂直合并
# pd.concat([df, df], axis=1) 横向合并
# pd.merge(df, df, on='ID', how='inner') 使用指定列进行合并，指定合并方式（内连接、左连接、右连接、外连接）

# 透视表
# print(pd.pivot_table(df, values='Salary', index='Status', aggfunc='mean')) 计算不同状态下的平均工资
# 数据透视表
# df = df.pivot(index='ID', columns='Status', values='Salary')
# print(pd.get_dummies(df, columns=['Status'])) 单独对数据状态进行0/1
# df['Name'].str.upper()
# df['Date'] = pd.to_datetime(df['Date'])
# print(df.resample('D').sum()) 将数据按天重新采样并求和

# print(df['Salary'].rolling(window=3).mean()) 计算滑动窗口的3天平均值
# print(df.plot(x='Age', y='Salary', kind='scatter'))
# 绘图展示
# ts = pd.Series(np.random.randn(1000), index=pd.date_range("1/1/2000", periods=1000))
# ts = ts.cumsum() 累加
# ts.plot()
# plt.show()

# df['DoubleAge'] = df['Age'].apply(lambda x: x * 2) 对年龄乘2
# df['Status'] = df['Status'].map({'Active': 1, 'Inactive': 0}) map映射值 修改值
# df['NewColumn'] = df['Column'].astype(float) 数据类型转换
# df['AgeGroup'] = pd.cut(df['Age'], bins=[20, 30, 40, 50], labels=['20-30', '30-40', '40-50']) 范围分组
# df['MeanSalaryByAge'] = df.groupby('AgeGroup')['Salary'].transform('mean') 通过某列分组后再求某列聚合函数
# df = df.sort_values('MeanSalaryByAge', ascending=False)

# print(df['Name'].isin(['Alice', 'Bob'])) 过滤指定列包含指定数据值的行
# df.duplicated(subset=['Name']) 过滤指定列重复值
# df.drop_duplicates(subset=['Name'], keep='first') 删除重复值 保留第一个

# df.nlargest(5, 'Salary')
# df.nsmallest(5, 'Salary') 获取工资最高的前5名和最低的前5名员工

# df = df['Status'].value_counts() 计算唯一值的频率
# pd.merge(df, df, left_on='ID', right_on='ID', suffixes=('_left', '_right'))

# print(df.at[1, 'Name']) 快速访问元素值
# df['Bonus'] = df['Salary'].mask(df['Salary'] > 60000, 'HighBonus')
# print(pd.crosstab(df['Status'], df['Age'])) 生成交叉表
# df.explode('Hobbies')

# print(df)
def test(str1):
        l = []
        for i in range(1, len(str1) + 1):
                s = list(combinations(str1, i))
                for j in s:
                        l.append(j)
        return l


if __name__ == '__main__':
    print(test('abc'))















