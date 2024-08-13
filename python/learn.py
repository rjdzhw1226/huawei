# + - * / % ** //
import my_module
import json
import time
import pyecharts

print("1 + 1 = ", 1 + 1)
print("2 * 2 = ", 2 * 2)
print("3 ** 4 =", 3 ** 4)
print("5 // 7 = ", 5 // 7)
print("5 % 7 = ", 5 % 7)
# 字符串可以单引号 双引号 三引号定义 strip 和 replace 方法 需要新变量来接收返回值
str_ = '123""'
str1_ = "\"你好"
number = 99182
print(str_)
print(type(str1_))
# % 占位符 完成字符串变量和数字的拼接 %s %d %f m .n 用于控制宽度 f"{}" f加大括号的占位方式
message = "IT黑马：%s" % str_
message_ = "数字1：%s , %s" % (number, number)

num1 = 11.25
任嘉冬_ = 12
print(任嘉冬_)
print(f"测试{message_}，测试数字{number}")
# 可以直接格式化表达式
print("%d" % 1 * 5)
print("%s" % (1 * 5))
sww = {1, '2', '3', 4}

# python的数制 0B表示二进制，0o表示八进制，OX表示十六进制，0D表示十进制。
form = hex(425)
oct(777)
print(form)

# 数字 int float complex bool 字符串 列表 元组 集合 字典
com = 4 + 2j
m_ = True
print(com)
print(m_)

result = 10 > 5
result_ = 10 < 5
print(result_)

name1 = 'itcast'
name2 = 'itheim'
print(f"{name1 >= name2}")


# 使用 end='' 不让print换行
# pass 一般用来占位 比如空函数使用pass使其顺利运行


def sample(n_sample):
    pass


# 全局变量需要在函数内修改需要 global 关键字


def testB():
    global name1
    name1 = "123"


testB()

print(name1)

# 容器 list tuple str set dict max_len(list) 2**63-1
# 列表[下标索引] 0 -1
nameList = ["123", '1233213', "12343234"]
ageList = ['1', True, 1234]
mixL = [nameList, ageList]

nameList.index("123")
nameList.extend(ageList)
nameList.insert(2, "55")
nameList.append("1567")
# del nameList[2]
# list_pop = nameList.pop(2)
# 从前到后删除第一个
# nameList.remove("55")
nameList.clear()
# [起始:结束:步长] 步长为负数代表反向取
list_ = nameList[1:2]
name_list_ = nameList[::1]
name_list_1 = nameList[::-1]

print(mixL)
print(mixL[-1])

# 元组 不可以被修改 len 长度 count 次数 index 找索引 取值和数组类似
t1 = (1, 'Hello', True)
t3 = tuple()
t4 = ("hello",)
t5 = ((), ())
print(t1[0])

for element in t1:
    print(element)

# 无序 去重 set 集合
my_set = {'1', '1', '2', '3', '1'}
my_set_1 = {'1', '4', '5'}
my_set.difference(my_set_1)
my_set.difference_update(my_set_1)
my_set.union(my_set_1)
print(my_set)

# 字典
dict_my = {"1": 18, "2": 34}

print(list(t1))

# open(name, mode, encoding) 打开文件方法 with open 会自动关闭文件 read 读完会停在最后指向的位置

f = open("D:/gf-config.xml", "r", encoding="UTF-8")
print(f.read())
print(f.readlines())
f.close()

# try except 异常处理 捕获指定异常

try:
    f = open("D:/gf-config.xml", "r", encoding="UTF-8")
except:
    print("出现异常")

# except Exception as e:

try:
    f = open("D:/gf-config.xml", "r", encoding="UTF-8")
except (NameError, ZeroDivisionError) as e:
    print(e)

try:
    f = open("D:/gf-config.xml", "r", encoding="UTF-8")
except (NameError, ZeroDivisionError) as e:
    print(e)
else:
    print("无异常")
finally:
    print("默认")

# __main__ 主函数 __all__ 变量控制import导入

# pip install 包名称 安装第三方包


data = [{"name": "王师傅", "age": "29"}, {"name": "魏师傅", "age": "30"}]


data = json.dumps(data, ensure_ascii=False)

print(json.loads(json.dumps(data)))
# 基础容器类型注解
var_1: int = 10
var_2: bool = True


class Student:  # 类对象类型注解
    pass


stu: Student = Student()

my_list: list = [1, 2, 3]
# 详细注解
my_list_1: list[int] = [1, 2, 3]
my_tuple: tuple = (1, 2, 3)
my_str: str = "itheima"

var_3 = 10  # type: int

# Union 联合注解表明一个变量有可能的类型

from typing import Union

my_list_2: list[Union[str, int]] = [1, 2, "itheima", "itcast"]
# python java 使用抽象类和接口做顶层设计 c++ 使用纯虚函数定义做顶层抽象


# def demo(name = 35, *age) 默认参数和不定长参数

def printinfo(arg1, *vartuple ):
   print("输出: ")
   print(arg1)
   for var in vartuple:
      print(var)
   return


sum1 = lambda arg1, arg2: arg1 + arg2

# 时间日期操作
print(time.time())
print(time.localtime(time.time()))
# 格式化成2016-03-20 11:45:39形式
time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
# 格式化成Sat Mar 28 22:24:24 2016形式
time.strftime("%a %b %d %H:%M:%S %Y", time.localtime())
# 将格式字符串转换为时间戳
a = "Sat Mar 28 22:24:24 2016"
time.mktime(time.strptime(a, "%a %b %d %H:%M:%S %Y"))

# dir()查看一个模块内的所有函数

