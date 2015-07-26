import scala.util.Random
import java.io.PrintWriter
import perceptron.perceptron
import breeze.linalg._
import breeze.plot._

object Main{
  
  def main(args:Array[String]){
    //学習データつくる
    //クラスずつにつくる
    val class1_data = DenseMatrix.rand(15,2)
    val class2_data = DenseMatrix.rand(15,2) * -1.0d
    
    //まず学習データを先にplotする
    val f = Figure()
    val p = f.subplot(0)
    
    p += plot(class1_data(::,0),class1_data(::,1),'.')
    p += plot(class2_data(::,0),class2_data(::,1),'.')
    p.xlabel = "x_1"
    p.ylabel = "x_2"
    p.title = "perceptron"
    
    
    
    
    
    //それぞれのクラスのラベルを用意
    val label1 = DenseMatrix.ones[Double](15,1)
    val label2 = DenseMatrix.ones[Double](15,1) * -1.0d
    
    //ラベル付与
    val class1 = DenseMatrix.horzcat(class1_data,label1)
    val class2 = DenseMatrix.horzcat(class2_data,label2)
    
    
    
   
    //学習データとして1つにまとめる．
    val train_data = DenseMatrix.vertcat(class1,class2)
    
    //-------学習データの生成完了-----------//

    //重みの初期値（適当に決めた)
    var w = DenseVector(-0.2, -0.5, -0.4)
    println("重みの初期値  "+ w)
    
    
    //パーセプトロン
    val perceptron = new perceptron
    for (i <- 1 to 4){
      println("-----"+ i + "周目 -----")
      for (j <-  0 to train_data.rows -1 ){
        
        var x:DenseVector[Double] = DenseVector(train_data(j,0),train_data(j,1),train_data(j,2))
        var new_w = perceptron.train(w, x, p,f)
        w := new_w
        println("学習後    "+ w)
        
      }
    }
    f.saveas("resurt.png")
    
    
  }
 
}