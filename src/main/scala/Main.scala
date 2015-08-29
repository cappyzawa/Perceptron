import scala.util.Random
import java.io.PrintWriter
import perceptron.perceptron
import breeze.linalg._
import breeze.plot._

object Main{

  def main(args:Array[String]){
    //学習データつくる
    //クラスずつにつくる
    //第1象限にランダムに15個つくる(class1)
    val class1_data = DenseMatrix.rand(15,2)
    //第3象限にランダムに15個つくる(class2)
    val class2_data = DenseMatrix.rand(15,2) * -1.0d

    val f = Figure()
    val p = f.subplot(0)

    //まず学習データを先にplotする
    p += plot(class1_data(::,0),class1_data(::,1),'.')
    p += plot(class2_data(::,0),class2_data(::,1),'.')
    //x_次元数
    p.xlabel = "x_1"
    p.ylabel = "x_2"
    p.title = "perceptron"



    //それぞれのクラスのラベルを用意
    //15行1列で全部1の行列作成（class1のラベル)
    val label1 = DenseMatrix.ones[Double](15,1)
    //15行1列で全部-1の行列作成（class2のラベル)
    val label2 = DenseMatrix.ones[Double](15,1) * -1.0d
    //このlabelは識別関数を計算するときに利用したいから1 or -1にした

    //ラベル付与
    val class1 = DenseMatrix.horzcat(class1_data,label1)
    val class2 = DenseMatrix.horzcat(class2_data,label2)

    //学習データとして1つにまとめる．
    val train_data = DenseMatrix.vertcat(class1,class2)

    //-------学習データの生成完了-----------//

    //重みの初期値（適当に決めた)
    var w = DenseVector(-0.2, -0.5, -0.4)

    //収束判定のflag　
    var flag = 1

    //パーセプトロン
    val perceptron = new perceptron
    for(i <- 1 to 10 if flag != 0){
      println("-----"+ i + "周目 -----")
      flag = 0 //ここで一回flagを0に戻す
      //データの行数の分だけ学習データは存在
      for (j <-  0 to train_data.rows -1 ){
        //学習データのj行をベクトル化する．
        var x:DenseVector[Double] = DenseVector(train_data(j,0),train_data(j,1),train_data(j,2))
        //学習しに行く
        var new_w = perceptron.train(w, x, p,f)
        if(w != new_w)  flag = 1 //1度でも誤識別があればflagは1になる．
        w := new_w
        f.saveas(i+"周目"+ (j+1) +"回目.png")
      }
    }
    
  }

}
