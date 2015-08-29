Perceptron
===============

機械学習の課題で出したパーセプトロンの実装

Scalaで実装した．

## Library

in ```build.sbt```

|Library|Version|
|:-----:|:-----|
|Scala|2.11.6|
|sbt|0.13.8|
|breeze|0.11.2|

機械学習ライブラリの[Breeze](https://github.com/scalanlp/breeze)を導入

> Breeze is a library for numerical processing.
> It aims to be generic, clean, and powerful without sacrificing (much) efficiency.

## Usage

```
cd Perceptron

sbt compile run
```

学習が始まりプロットされ始める．

学習が終わると更新がおわり，図が残ったままになってしまうため，コンソール上で ```control c``` で中断する．(要改善)

## Edit
```src/main/scala/Perceptorn.scala``` の ```rho``` は学習率
任意の値に編集して実行可能
