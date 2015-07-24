package perceptron{
  
  import scala.util.Random
  
  class Perceptron{
    
    def train(data:List[(Double, Double, Double)], w:List[Double] = List[Double](0.0,0.0,0.0), limit:Int = 10000): List[Double]={
      
      val r = new Random
      //重みの移動先new_w
      val new_w:List[Double] = this.trial(r.shuffle(data), w)
      if(w == new_w || limit <=0){
        return new_w
      }else{
        train(data, new_w, limit-1)
      }
    }
    
    //ここちゃんと考えればなんかみえてきそう！
    private def trial(data:List[(Double,Double,Double)], w:List[Double]): List[Double] = {
      var new_w:List[Double] = w
      
      data.foreach { p =>
        
        val pred = this.predicate(this.phi(p._1, p._2), new_w)
        if(pred != p._3){
          new_w = new_w.zip(this.phi(p._1, p._2)).map((t) =>
            t._1 + p._3 * t._2  
          )
        }
        
      }
      
      new_w
      
    }
    
    private def predicate(w: List[Double], phi: List[Double]):Double = {
      if(this.innerProduct(w,phi) >= 0.0) 1.0 else -1.0
    }
    
    private def innerProduct(a: List[Double], b: List[Double]): Double={
      if(a.size != b.size){
        throw new RuntimeException("list size isn't equal.")
      }
      
      val products:List[Double] = a.zip(b).map((t) => t._1 * t._2)
      products.reduceLeft{(a,b) => a + b}
     }
    
    private def phi(x:Double, y:Double): List[Double]= {
      List[Double](x *1.0, y*1.0, 1.0)
    }
  }
}