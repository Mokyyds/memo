# ![logo](app/src/main/res/mipmap-mdpi/ic_launcher.png) memo

[pyMemo](https://github.com/SEALiu/PyMemo)的Android版本.一边学习一边开发中.

## 介绍

### 基本介绍
本app旨在管理你记忆力. 

通过创建记忆库并添加记忆材料, 在一段持续较长的碎片时间内通过 `记忆`>>>`遗忘`>>>`记忆`>>>`遗忘`>>>`记忆`...... 这些阶段, 用这种持续增加间隔时间的重复来强化你的记忆. 

### 原理
![受到间隔重复修正后的艾宾浩斯遗忘曲线](/screenshots/1.gif)

**<small> * 可以发现, 经过多次间隔不断增加的复习, 记忆保持百分比越趋近理想曲线.</small>**

### 规划
记忆材料包括但不限于英语单词, 但是第一个版本以此为主要和唯一的记忆材料(选择英语单词为第一版本的记忆材料原因在于, 记忆材料的内容为文字). 在以后的版本更新中会先后加入 `数学公式`, `化学方程式`, `乐谱`, `歌词`等等任何你想要记住的内容(记忆材料的类型增加: 图片, 音频, 视频). 

和[pyMemo](https://github.com/SEALiu/PyMemo)相同, 使用重复间隔理论帮助大家更好的记忆. 初期会以英语单词位主要和唯一的记忆材料, 相比[pyMemo](https://github.com/SEALiu/PyMemo) 会增加当时由于时间关系没有完成的进度统计功能, 音标显示功能, 和单词查询功能. 支持自定义词库和导入共享词库(该功能第一个版本待定).

![screenRecord](/screenshots/device-2016-06-30-103405.gif)

## 必备

- Android SDK  Min v19
- Android SDK Target v23
- Android Build Tools v23.0.3
- Android Support Repository

## 开始

使用`Android Studio` 导入项目

## 联系

- [issues](https://github.com/SEALiu/memo/issues "推荐")
- sealiu0217@gmail.com

## License

[GNU AFFERO GENERAL PUBLIC LICENSE](http://www.gnu.org/licenses/gpl.html)
