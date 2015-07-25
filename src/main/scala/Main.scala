import scala.util.Random
import java.io.PrintWriter
import perceptron.perceptron
import breeze.linalg._

object Main{
  
  def main(args:Array[String]){
    //学習データつくる
    //クラスずつにつくる
    val class1_data = DenseMatrix.rand(15,2)
    val class2_data = DenseMatrix.rand(15,2) * -1.0d
    
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
    println("初期値"+ w)
    
    //パーセプトロン
    val perceptron = new perceptron
    for (i <- 0 to 10){
      println(i + "週目")
      for (j <-  0 to 29){
        var x:DenseVector[Double] = DenseVector(train_data(j,0),train_data(j,1),train_data(j,2))
        var new_w = perceptron.train(w, x)
        w := new_w
        println(w)
      }
    }
    
    
  }
 
}