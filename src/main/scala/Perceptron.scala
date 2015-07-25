package perceptron{
  
  import breeze.linalg._
  
  class perceptron{
    
    def train(w:DenseVector[Double], x:DenseVector[Double]):DenseVector[Double] = {
      println("もってきたx" + x)
      val g = this.makeFunction(w,x)
      println(g)
      
      
      if(x(2) == 1.0){ //クラス1に属してるならgは0より大きいはず
        if(g <= 0.0){
          println("class1なのに")
          val new_w = this.updateWights(w,x)
          println(new_w)
          return new_w
        }
        else{
          println("class1順当")
          return w
        }
      }
      else{
        if(g >= 0.0){
          println("class2なのに")
          val new_w = this.updateWights(w,x)
          println(new_w)
          return new_w
        }
        else{
          println("class2順当")
          return w
        }
      }
    }
    
    private def makeFunction(w:DenseVector[Double], x:DenseVector[Double]):Double ={
      return w(0) + w(1)*x(1) + w(2)*x(2)
  }
    
    private def updateWights(w:DenseVector[Double], x:DenseVector[Double]):DenseVector[Double] = {
      println("きた")
      //正の定数rhoを決める
      val rho = 0.02
      //ベクトルxの作成 x_0は常に1
      val xvec = DenseVector(1.0,x(0),x(1))
      //可読性を高めるため一応labelについて記述
      val label = x(2)
      
      val new_w = w + rho * label * xvec
      return new_w
    }
  }
  
  
 }