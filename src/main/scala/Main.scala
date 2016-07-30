import breeze.linalg._
import breeze.plot._

object Main {

  def main(args: Array[String]) {
    //学習データつくる
    //クラスずつにつくる
    //第1象限にランダムに15個つくる(class1)
    val firstQuadrant = DenseMatrix.rand(15, 2)
    //第3象限にランダムに15個つくる(class2)
    val thirdQuadrant = DenseMatrix.rand(15, 2) * -1.0d

    val f = Figure()
    var p = f.subplot(0)

    //まず学習データを先にplotする
    p += plot(firstQuadrant(::, 0), firstQuadrant(::, 1), '.')
    p += plot(thirdQuadrant(::, 0), thirdQuadrant(::, 1), '.')
    //x_次元数
    p.xlabel = "x_1"
    p.ylabel = "x_2"
    p.title = "Perceptron"



    //それぞれのクラスのラベルを用意
    //15行1列で全部1の行列作成（class1のラベル)
    val class1Label = DenseMatrix.ones[Double](15, 1)
    //15行1列で全部-1の行列作成（class2のラベル)
    val class2Label = DenseMatrix.ones[Double](15, 1) * -1.0d
    //このlabelは識別関数を計算するときに利用したいから1 or -1にした

    //ラベル付与
    val class1 = DenseMatrix.horzcat(firstQuadrant, class1Label)
    val class2 = DenseMatrix.horzcat(thirdQuadrant, class2Label)

    //学習データとして1つにまとめる．
    val trainData = DenseMatrix.vertcat(class1, class2)

    //-------学習データの生成完了-----------//

    //重みの初期値（適当に決めた)
    var w = DenseVector(-0.2, -0.5, -0.4)

    //収束判定のflag　
    var flag = 1

    //パーセプトロン
    for (i <- 1 to 10 if flag != 0) {
      if (flag != 0) {
        println("-----" + i + "周目 -----")
        flag = 0 //ここで一回flagを0に戻す
        //データの行数の分だけ学習データは存在
        for (j <- 0 until trainData.rows) {
          //学習データのj行をベクトル化する．
          val x: DenseVector[Double] = DenseVector(trainData(j, 0), trainData(j, 1), trainData(j, 2))
          //学習しに行く
          var new_w = Perceptron.train(w, x, p, f)
          if (w != new_w) {
            flag = 1 //1度でも誤識別があればflagは1になる．
          }
          w := new_w
          f.saveas(i + "周目" + (j + 1) + "回目.png")
        }
      } else {
        println("終了")
      }
    }

  }

}
