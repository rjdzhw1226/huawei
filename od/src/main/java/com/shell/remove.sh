touch rjd_{1..5}_finished.jpg

mv rjd_1_finished.jpg rjd_1.jpg

f=rjd_1_finished.jpg

echo ${f//_finished/}

mv $f `echo ${f//_finished/}`

ls *fin*.jpg

for file_name in `ls *fin*jpg`;do echo $file_name;done

for file_name in `ls *fin*jpg`;do mv $file_name `echo ${file_name//file_name/}`;done

find ${dir_path} -name '*.tar.gz' - type f -mtime +7|xargs rm -f

(cd ~;pwd;ls) #使用小括号括起来 称之为进程列表 开启子shell运行

echo $BASH_SUBSHELL #检测是否在子shell中 0代表当前shell n为开启子shell运行 可嵌套


