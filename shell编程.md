[TOC]



# Shell变量

定义变量时不加\$符, 使用变量时需要加\$符号.

## 定义变量

name="content"

注意, **变量和等号之间不能有空格!** 同时需要遵循命名规则.

## 使用变量

echo $name

或

echo ${name}

注意有\$符号.

**花括号的使用**: 如下面的语句

echo "I am good at ${skill}Script"

如果不给skill变量加花括号, 解释器会把\$skillScript理解成为一个变量.

## 重新定义变量

就是将其他值赋给变量.

name="tome" \# 定义

name="tom" \# 重新赋值

注意: 第二次赋值不使用$.

## 只读变量

使用**readonly**命令将变量定义为只读变量. 只读变量的值不能修改.

name="tom"

readonly name

## 删除变量

unset name

注意: 没有使用$

> 总结: **变量的定义/赋值/删除都不使用\$, 变量的使用才使用\$.**

## 变量的类型

- 局部变量
- 环境变量
- shell变量



# Shell字符串

字符串可以使用**单引号**和**双引号**, 也可以**不用引号**.

str=this is a string

## 单引号和双引号的区别

单引号

 - 单引号里的任何字符都会原样输出, 单引号字符串中的变量是无效的.
 - 单引号字符串中不能出现单独一个的单引号(**对单引号使用转义字符也不行**), 但可成对出现, 作为字符串拼接使用

双引号(优势)

- 双引号里可以有变量
- 双引号里可以出现转义字符

## 拼接字符串

### 使用双引号拼接

your_name="runoob"

greeting="hello, "\$your_name" !" \#相当于省略了java中的+号("hello, " + \$your_name + " !")

greeting="hello, ${your_name} !"

### 使用单引号拼接

greeting='hello, '$your_name' !'

greeting='hello, ${your_name} !'

## 获得字符串的长度

string="abcd"

echo ${**#**string} \# 使用#号

## 提取子字符串

string="runoob is a great site"

echo ${string:1:4} #输出unoo



# Shell数组

## 定义数组

shell支持一维数组(不支持多维数组), 数组元素用**空格**隔开.

array=(value1 value2 value3)

或者

array=(

value1

value2

value3

)

可以单独定义数组各个分量

array[0]=value1

可以不使用连续的下标, 而且下标的范围没有限制.

## 读取数组

\${array[index]}

注: 使用数组中的所有元素 \${array[**@**]}

## 获取数组长度

获取数组的长度与获取字符串长度的方法相同

\${#array[@]}

或

\${#array[*]}

取数组中单个元素的长度

\${#array[index]}



# Shell注释

### 单行注释

以#开头的行就是注释

### 多行注释

:<<EOF

content...

EOF

也可以将EOF换成其他符号, 例如:

:<<'

content...

'

或

:<<!

content...

!



# Shell传递参数

例如命令行有这样的输入 \$ ./test.sh 1 2 3

如果想获得输入的 "test.sh"以及"1", "2", "3", 需要使用**\$n** .

**\$0 程序名** 即"test.sh"

**\$1以后 参数值**

还有几个特殊字符用来处理参数

| 符号 | 说明                                                         |
| ---- | ------------------------------------------------------------ |
| \$#  | 传递到脚本的参数个数                                         |
| \$*  | 以一个单字符串线束所有想脚本传递的参数. (将程序名之后的所有参数看作一整个字符串) |
| \$\$ | 脚本运行的当前进程ID号                                       |
| \$!  | 后台运行的最后一个进程ID号                                   |
| \$@  | 与\$*相同, 但是使用时加引号, 并在引号中返回每个参数.<br />如"\$@", 会以"\$1", "\$2", "\$3"的形式输出所有参数 |
| $-   | 显示Shell使用的当前选项, 与set命令功能相同                   |
| $?   | 显示最后命令的退出状态. 0表示没有错误, 其他任何值表示有错误  |

注: 

\$\*和\$\@的区别: 只有在双引号中体现出来. 假设在脚本运行时写了三个参数1 2 3, 则"\*"等价于"1 2 3"(传递了一个参数), 而"\@"等价于"1""2""3"(传递了三个参数)



# Shell基本运算符

原生bash不支持简单的数学运算(?), 但是可以通过其他命令来实现.

注: bash可以通过**$[1+2]**的方式进行简单的数学运算.

注意: 使用的是**反引号**"&#96;"

例如, 两个数相加

val=&#96;expr 2 + 2&#96;

注意:

- 表达式和运算符之间要有**空格**!
- 完整的表达式要被&#96; &#96; 包围

## 算数运算符

| 运算符 | 说明                     | 举例                                      |
| ------ | ------------------------ | ----------------------------------------- |
| +      | 加                       | &#96;expr \$a + \$b&#96;                  |
| -      | 减                       | &#96;expr \$a - \$b&#96;                  |
| *      | 乘                       | &#96;expr \$a \\* \$b&#96; 乘号前必须加\\ |
| /      | 除                       | &#96;expr \$b / \$a&#96;                  |
| %      | 取余                     | &#96;expr \$b % \$a&#96;                  |
| =      | 赋值                     | a=$b 将变量b的值赋给a                     |
| ==     | 相等, 用于比较两个数字   | [ \$a == \$b ]                            |
| !=     | 不相等, 用于比较两个数字 | [ \$a != \$b ]                            |

注: 条件表达式要放在方括号内, 并且要有空格. 例如[\$a==\$b]是错误的.

## 关系运算符

关系运算符只支持数字, 不支持字符串, 除非字符串的值是数字.

| 运算符 | 说明                             | 举例            |
| ------ | -------------------------------- | --------------- |
| -eq    | 检测两个数是否相等, 相等返回true | [ \$a -eq \$b ] |
| -ne    | 检测两个数是否不相等             | [ \$a -ne \$b ] |
| -gt    | 检测左边的数是否大于右边         | [ \$a -gt \$b ] |
| -ge    | 大于等于                         | [ \$a -ge \$b ] |
| -lt    | 检测左边的数是否小于右边         | [ \$a -lt \$b ] |
| -le    | 小于等于                         | [ \$a -le \$b ] |

注: 假设a和b均为数字

```shell
a=10
b=20

if [$a -eq $b]
then
	...
fi
```

## 布尔运算符

| 运算符 | 说明 | 举例                          |
| ------ | ---- | ----------------------------- |
| !      | 非   | [ ! false ]                   |
| -o     | 或   | [ \$a -lt 20 -o \$b -gt 100 ] |
| -a     | 与   | [ \$a -lt 20 -a \$b -gt 100 ] |

## 逻辑运算

| 运算符 | 说明   | 举例                               |
| ------ | ------ | ---------------------------------- |
| &&     | 逻辑与 | [[ \$a -lt 100 && \$b -gt 100 ]]   |
| \|\|   | 逻辑或 | [[ \$a -lt 100 \|\| \$b -gt 100 ]] |

注: 假设a和b均为数字

## 字符串运算符

| 运算符 | 说明                               | 举例           |
| ------ | ---------------------------------- | -------------- |
| =      | 检测两个字符是否相等               | [ \$a = \$b ]  |
| !=     | 检测两个字符是否不等               | [ \$a != \$b ] |
| -z     | 检测字符串长度是否为0              | [ -z $a ]      |
| -n     | 检测字符串长度是否不为0            | [ -n "\$a"]    |
| $      | 检测字符串是否为空, 若不空返回true | [\$a]          |

注: 假设a为"abc", b为"efg"

## 文件测试运算符

基本格式: [ -b $file ]

| 操作符  | 说明                                                     |
| ------- | -------------------------------------------------------- |
| -b file | 检测文件是否是块设备文件                                 |
| -c file | 检测文件是否是字符设备文件                               |
| -d file | 检测文件是否是目录                                       |
| -e file | 检测文件(包括目录)是否存在                               |
| -f file | 检测文件是否是普通文件<br />(既不是目录, 也不是设备文件) |
| -g file | 检测文件是否设置了SGID位                                 |
| -k file | 检测文件是否设置了粘着位(Sticky Bit)                     |
| -p file | 检测文件是否是有名管道                                   |
| -u file | 检测文件是否设置了SUID位                                 |
| -r file | 检测文件是否可读                                         |
| -w file | 检测文件是否可写                                         |
| -x file | 检测文件是否可执行                                       |
| -s file | 检测文件是否为空, 不空则返回true                         |
| -S file | 判断文件是否socket                                       |
| -L file | 检测文件是否存在并且是一个符号链接                       |



# Shell echo命令

用于字符串的输出

## 显示普通字符串

echo "It is a test"

## 显示转义字符

echo "\\"It is a test\\"" #输出为"It is a test"

## 显示变量

**read**命名从标准输入中读取一行, 并把输入行的每个字段的值指定给shell变量.

read name

echo "\$name It is a test"

## 显示换行

echo -e "OK! \n" #-e 开启转义

## 显示不换行

echo -e "ok! \c" #\c 不换行

## 显示结果定向到文件

echo "it is a test" > file

## 原样输出字符串, 不进行转义或去变量(用单引号)

echo '\$name\"' #输出为\$name\"

## 显示命令执行结果

echo &#96;date&#96; #这里使用反引号&#96;



# Shell printf命令

printf命令类似于C语言库里的printf()函数. printf由POSIX标准定义, 因此比echo有更好的移植性. 默认printf不会像echo自动添加换行, 可以手动添加\\n.

## printf命令的语法

printf format-string [arguments...]

参数说明:

format-string: 格式控制字符串

arguments: 参数列表



# Shell test命令(类似于汇编中的条件判断)

test命令用于检查某个条件是否成立, 它可以进行数值, 字符和文件三方面的测试.

## 数值测试

| 参数 | 说明(为真的条件) |
| ---- | ---------------- |
| -eq  | 等于             |
| -ne  | 不等于           |
| -gt  | 大于             |
| -ge  | 大于等于         |
| -lt  | 小于             |
| -le  | 小于等于         |

代码中的[]执行基本的算数运算. 例如:

```shell
#!/bin/bash
a=5
b=6
result=$[a+b]
echo "result is: $result"

num1=100
num2=100
if test $[num1] -eq $[num2] #条件判断
then
	echo "equal"
else
	echo "not equal"
fi
```

## 字符串测试

| 参数      | 说明(为真的条件)  |
| --------- | ----------------- |
| = (或==)  | 等于              |
| !=        | 不等于            |
| -z 字符串 | 字符串的长度为0   |
| -n 字符串 | 字符串的长度不为0 |

```shell
num1="ru1noob"
num2="runoob"
if test $num1 = $num2 #注意, 必须在等号两端加空格!
then
	echo "equal"
else
	echo "not equal"
fi
```

## 文件测试

| 参数      | 说明(为真的条件)             |
| --------- | ---------------------------- |
| -e 文件名 | 文件存在                     |
| -r 文件名 | 文件存在且可读               |
| -w 文件名 | 文件存在且可写               |
| -x 文件名 | 文件存在且可执行             |
| -s 文件名 | 文件存在且至少有一个字符     |
| -d 文件名 | 文件存在且为目录             |
| -f 文件名 | 文件存在且为普通文件(非目录) |
| -c 文件名 | 文件存在且为字符型特殊文件   |
| -b 文件名 | 文件存在且为块特殊文件       |

```shell
cd /bin
if test -e ./bash
then
	echo "the file exists."
else
	echo "the file does not exist."
fi
```

## 逻辑运算符--测试条件的连接

Shell提供了"与"(-a), "或"(-o), "非"(!)三个逻辑运算符用于将测试条件拼接起来, 优先级为: ! 大于 -a 大于 -o.

```shell
cd /bin
if test -e ./notFile -o -e ./bash
then
	echo "至少有一个文件存在"
else
	echo "两个文件都不存在"
fi
```



# Shell流程控制

## if

if condition1

then

​	command0

elif condition2

then

​	command1

else

​	command2

fi

或

if condition; then command; fi

注意: 写成一行需要加";". 末尾的fi是if倒过来拼写, 后面还会遇到类似的情况.

## for

for var in item1 item2 ... itemN

do

​	command

done

实例:

```shell
for loop in 1 2 3 4
do
	echo "the value is $loop"
done

for str in 'This is a string'
do
	echo $str
done
```

## while

while condition

do

​	command

done

实例:

```shell
i=1
while(( $i<=5 ))
do
	echo $i
	let "i++"
done

#while循环可用于读取键盘信息.
#例: 输入信息被设置为变量FILM, 按<Ctrl-D>结束循环
echo '按下<CTRL-D>退出'
echo -n '输入'
while read FILM
do
	echo "$FILM是一个号网站"
done
```

## 无限循环

while :

do

​	command

done

或

while true

do

​	command

done

或

for (( ; ; ))

## until循环

until循环执行一系列命令直到条件为true时停止.

until循环与while循环在处理方式上刚好相反. 一般while循环优于until循环, 但某些时候until循环更有用.

until condition

do

​	command

done

注: condition一般为条件表达式, 如果返回值为false, 则继续执行循环体内的语句, 否则跳出循环. (相当于对while的循环条件取反.)

## case(类似switch)

case value in

mode1)

​	command1

​	;;

mode2)

​	command2

​	;;

*)	#default

​	command3

​	;;

esac

注意:

case在取值后面必须为单词in, 每个模式必须以右括号结束. 

取值可以是变量或常数, 可以使用正则表达式.

匹配发现取值符号某个模式后, 其间所有命令开始执行直至;;.

取值将检测匹配的每一个模式. 一旦模式匹配成功, 则执行完匹配模式相应命令后不再继续其他模式. 如果无一模式匹配, 使用星号*捕获该值, 在执行后面的命令.

## 跳出循环

break

continue



# Shell函数

## 无参函数

[ function ] funname [()]

{

​	action;

​	[return int;]

}

说明:

1. 可以使用function fun()定义, 也可以直接fun()定义, 不带任何参数
2. 参数返回, 可以显式加: return 返回, 如果不加, 将以最后一条命令运行的结果作为返回值. return后面跟数值n (0~255)

例:

```shell
demoFunc(){
	echo "the first shell function."
}

demoFunc()

# 带有return的函数
funWithReturn(){
	echo "对两个数字进行加法运算"
	echo "输入第一个数字: "
	read aNum
	echo "输入第二个数字: "
	read bNum
	return $(($aNum+$bNum))
}

funWithReturn
```

## 有参函数

在函数体内部通过\$n的形式来获取参数的值. \$1表示第一个参数. (与命令行去参数相同)

注: 当获取的参数位置>10时, 应该使用\${n}(而不是\$n). 如第十个参数为${10}.

例:

```shell
funWithParam(){ #括号里什么都没有
	echo "$1"
	echo "$2"
}

funWithParam 1 2
```

有几个特殊符号来处理参数:

| 参数处理 | 说明(与数组的特殊符号说明相同)                              |
| -------- | ----------------------------------------------------------- |
| $#       | 传递到脚本或函数的参数个数                                  |
| $*       | 以一个单字符串显示所有向脚本传递的参数                      |
| $$       | 脚本运行的当前进程ID号                                      |
| $!       | 后台运行的最后一个进程ID号                                  |
| $@       | 与$*相同, 但是使用时加引号, 并在引号中返回每个参数          |
| $-       | 显示shell使用的当前选项, 与set命令功能相同                  |
| $?       | 显示最后命令的退出状态. 0表示没有错误, 其他任何值表示有错误 |



# Shell输入/输出重定向

大多数UNIX系统命令从你的终端接收输入并将所产生的输出发送回你的终端. 

一个命令通常从一个叫标准输入的地方读取输入, 默认情况下, 这恰好是你的终端. 同样, 一个命令通常将其输出写入到标准输出, 默认情况下, 这也是你的终端.

## 重定向命令列表

| 命令            | 说明                                          |
| --------------- | --------------------------------------------- |
| command > file  | 将输出重定向到file                            |
| command < file  | 将输入重定向到file                            |
| command >> file | 将输入以追加的方式重定向到file                |
| n > file        | 将文件描述符为n的文件重定向到file             |
| n >> file       | 将文件描述符为n的文件以追加的方式重定向到file |
| n >$ m          | 将输出文件m和n合并                            |
| n <$ m          | 将输入文件m和n合并                            |
| << tag          | 将开始标记tag和结束标记tag之间的内容作为输入  |

注: 文件描述符0通常是标准输入(STDIN), 1是标准输出(STDOUT), 2是标准错误输出(STDERR). 

可以将上述命令进行组合, 如command < file1 > file2

## 深入理解重定向

一般情况下, 每个UNIX/LINUX命令运行时会自动打开三个文件:

- stdin(标准输入文件): stdin的文件描述符为0, Unix程序默认从stdin读取数据.

- stddout(标准输出文件): stdout的文件描述符为1, Unix程序默认向stdout输出数据.

- stderr(标准错误文件): stderr的文件描述符为2, Unix程序会向stderr流中写入错误信息.

默认情况下, command > file 将stdout重定向到file, command < file 将stdin重定向到file.

**将stderr重定向到文件file**: command 2>file

**将etderr追加到文件file末尾**: command 2>>file

**将stdout和stderr合并后重定向到file**: command > file 2>&1

**将stdin和stdout都重定向**: command < file1 > file2

## /dev/null 文件

如果希望之星某个命令, 但又不希望在屏幕上线束输出结果, 可以将输出重定向到/dev/null:

command > /dev/null

/dev/null是一个特殊文件, 写入到它的内容会被丢弃; 如果尝试读取该文件, 则什么也读不到.

**/dev/null文件非常有用, 会起到"禁止输出"的效果.**

## Here Document

Here Document是shell中一种特殊的重定向方式, 用来将输入重定向到一个交互式shell脚本或程序.

## 基本形式

command << delimiter

​	document

delimiter

它的作用是将两个delimiter之间的内容作为输入传递给command.

注意:

- 结尾delimiter必须顶格写, 前面不能有任何字符, 后面也不能有任何字符, 包括空格和tab缩进

- 开始delimiter前后的空格会被忽略掉

例:

```shell
$ wc -l << EOF
    欢迎来到
    菜鸟教程
    www.runoob.com
EOF
```



# Shell文件包含

shell可以包含外部脚本. 这样可以方便地封装一些公用的代码作为一个独立的文件.

## 语法格式

. filename # 注意点号(.)和文件名中间有一个空格

或

**source** filename



例:

```shell
#test1.sh
#!/bin/bash

url="http://www.runoob.com"
```

```shell
#test2.sh
#!/bin/bash

#使用 . 号来引用test1.sh 文件
. ./test1.sh

# 或者使用以下包含文件代码
# source ./test1.sh

echo "菜鸟教程官网地址：$url"
```

