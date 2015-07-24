package perceptron{
  
  import scala.util.Random
  
  class Perceptron{
    
    def train(x_1:Double, x_2:Double, label:Double, w:List[Double]): List[Double] = {
      //まずは識別関数gを求める必要がある．
      var g:Double = this.createFunction(x_1,x_2,w)
      
      //g>0ならクラス1，g<0ならクラス2である．
      if(label == 1.0){
        if(g <= 0){
          //誤識別してる場合
          var new_w:List[Double] = this.updateWights(x_1,x_2,label,w)
          return new_w
        }
        else{
          return w
        }
      }
      else{
        if(g >= 0){
          //誤識別してる場合
          var new_w:List[Double] = this.updateWights(x_1,x_2,label,w)
          return new_w
        }
        else{
          return w
        }
      }
    }
    
    private def createFunction(x_1:Double, x_2:Double, w:List[Double]):Double = {
      return w(0)+ w(1)*x_1 + w(2)*x_2 //x_0は常に1
    }
    
    private def updateWights(x_1:Double, x_2:Double, label:Double, w:List[Double]):List[Double] = {
      //rhoは0.5にしとく
      var rho = 0.5
      if(label == 1.0){
        var new_w_0 = w(0) + rho
        var new_w_1 = w(1) + rho*x_1
        var new_w_2 = w(2) + rho*x_2
        var new_w:List[Double] = List[Double](new_w_0,new_w_1,new_w_2)
        return new_w
      }
      else{
        var new_w_0 = w(0) - rho
        var new_w_1 = w(1) - rho*x_1
        var new_w_2 = w(2) - rho*x_2
        var new_w:List[Double] = List[Double](new_w_0,new_w_1,new_w_2)
        return new_w
      }
     
    }
    
        
        //private def updateWeight(data:List[Double], w:List[Double]): List[Double] = {
          
        //}
   

        private def trial(data:List[(Double, Double, Double)], w:List[Double]): List[Double] = {
            var new_w:List[Double] = w

            data.foreach { p =>

                val pred = this.predicate(this.phi(p._1, p._2), new_w)
                if (pred != p._3) {
                    new_w = new_w.zip(this.phi(p._1, p._2)).map((t) =>
                        t._1 + p._3 * t._2 //
                    )
                }
            }

            new_w
        }

        private def predicate(w: List[Double], phi: List[Double]):Double = {
            if (this.innerProduct(w, phi) >= 0.0) 1.0 else -1.0
        }

        private def innerProduct(a: List[Double], b: List[Double]): Double = {
            if (a.size != b.size) {
                throw new RuntimeException("list size isn't equal.")
            }

            val products:List[Double] = a.zip(b).map((t) => t._1 * t._2)
            products.reduceLeft {(a,b) => a + b}
        }

        //重みの更新
        private def phi(x:Double, y:Double):List[Double] = {
            List[Double](x * 1.0, y * 1.0, 1.0)
        }
    
    
    
   
  }
}