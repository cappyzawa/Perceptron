package perceptron{
  
  import breeze.linalg._
  import breeze.plot._
  
  class perceptron{
    
    def train(w:DenseVector[Double], x:DenseVector[Double], p:Plot, f:Figure):DenseVector[Double] = {
      val g = this.makeFunction(w,x)
      println(g)

      val x_2 = this.makeLine(w,x)
      
      
      
      if(x(2) == 1.0){ //クラス1に属してるならgは0より大きいはず
        if(g <= 0.0){
          println("学習する")
          val new_w = this.updateWights(w,x)
          return new_w
        }
        else{
          println("OK")
          return w
        }
      }
      else{
        if(g >= 0.0){
          println("学習する")
          val new_w = this.updateWights(w,x)
          return new_w
        }
        else{
          println("OK")
          return w
        }
      }
    }
    
    private def makeFunction(w:DenseVector[Double], x:DenseVector[Double]):Double ={
      return w(0) + w(1)*x(1) + w(2)*x(2)
  }
    
    private def updateWights(w:DenseVector[Double], x:DenseVector[Double]):DenseVector[Double] = {

      //正の定数rhoを決める
      val rho = 0.02
      //ベクトルxの作成 x_0は常に1
      val xvec = DenseVector(1.0,x(0),x(1))
      //可読性を高めるため一応labelについて記述
      val label = x(2)
      
      val new_w = w + rho * label * xvec
      return new_w
    }
    
    private def makeLine(w:DenseVector[Double], x:DenseVector[Double]):DenseMatrix[Double] ={
      //どうやら点と点を結ぶらしい
      //x_1 = -1のときと,x_1 = 1のときを結べばいい
      //ここではx_1 = x(0)であるから
      val x_2_minus = (w(1)*(-1.0d)-w(0))/w(2)
      val x_2_minus_mat = DenseMatrix(-1.0,x_2_minus)
      val x_2_plus = (w(1)-w(0))/w(2)
      val x_2_plus_mat = DenseMatrix(1.0,x_2_plus)
      val x_2 = DenseMatrix.vertcat(x_2_minus_mat.t,x_2_plus_mat.t)
      return x_2
    }
  }
  
  
 }