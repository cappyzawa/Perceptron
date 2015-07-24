import scala.util.Random
import java.io.PrintWriter
import perceptron.Perceptron

object Main{
  
  def main(args:Array[String]){
    //テストデータつくる
    val data:List[(Double,Double,Double)] = this.makeTestData(30)
    //作り終えたらperceptronの学習しにいく
    val perceptron = new Perceptron
    //重みの初期値（適当に決めた)
    var w:List[Double]=List[Double](0.2, -0.1, 0.3)
    println(w)
    
    //学習データの数だけ施行する
    for(i <- 0 to data.size){
      //毎回学習の前にplotする
      
      //perceptron
      var x_1 = data(i)._1
      var x_2 = data(i)._2
      var label = data(i)._3
      w = perceptron.train(x_1,x_2,label,w)
      println(w)
      
    }
    
    //tsvファイルに書き出し
    
    val up = new PrintWriter("output/upper.tsv")
    val lo = new PrintWriter("output/lower.tsv")

    
    data.foreach({p =>
      //scoreは識別関数の値
      //p._1はdataの1個目の値(val x)
        val score = w(0) * p._1 + w(1) + p._2 + w(2)
        if (score >= 0.0){
          up.println("%f\t%f".format(p._1, p._2))
        }else{
          lo.println("%f\t%f".format(p._1, p._2))
        }
    })
    up.close
    lo.close
    
  }
  
  private def makeTestData(n:Int): List[(Double, Double, Double)] = {
    
    //２次元のテストデータの作成
    //xとyをそれぞれランダムでつくり組み合わせる．
    val r = new Random
    (1 to n).toList.map({(n) =>
      val x = (r.nextDouble()) 
      val y = (r.nextDouble()) 
      //tはどちらのクラスに属するかの判定
      //y＝2.0xより上にあればクラス1(1.0)，下にあればクラス2(-1.0)
      val t = if (this.h(x,y) >= 0.0) 1.0 else -1.0
      (x,y,t)
    })
  }
  
  //y = 2.0xをクラスの境界とする．
  private def h(x:Double, y:Double): Double = {
    2.0*x - y
  }
}