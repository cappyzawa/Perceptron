

import breeze.linalg._
import breeze.plot._

object Perceptron {

  def train(w: DenseVector[Double], x: DenseVector[Double], p: Plot, f: Figure): DenseVector[Double] = {
    //まず識別関数にw,xを代入して値を計算
    val g = this.makeFunction(w, x)

    //決定境界をつくる(描写するために作ったメソッド,少し冗長になってしまったかもしれない．)
    val line = this.makeLine(w, x)
    //行列で表現した2点を'-'で結ぶ これが決定境界になる．
    p += plot(line(::, 0), line(::, 1), '-')


    if (x(2) == 1.0) {
      //クラス1に属してるならgは0より大きいはず
      if (g <= 0.0) {
        val new_w = this.updateWights(w, x)
        return new_w
      }
      else {
        return w
      }
    }
    else {
      if (g >= 0.0) {
        val new_w = this.updateWights(w, x)
        return new_w
      }
      else {
        return w
      }
    }
  }

  private def makeFunction(w: DenseVector[Double], x: DenseVector[Double]): Double = {
    return w(0) + w(1) * x(1) + w(2) * x(2)
  }

  //誤識別時の重みの更新
  private def updateWights(w: DenseVector[Double], x: DenseVector[Double]): DenseVector[Double] = {

    //正の定数rhoを決める
    val rho = 0.2
    //ベクトルxの作成 x_0は常に1
    val xvec = DenseVector(1.0, x(0), x(1))
    //可読性を高めるため一応labelについて記述
    val label = x(2)

    //class1ならlabelは1，class2ならlabelは-1であるからこの式は成り立つ
    val new_w = w + rho * label * xvec
    return new_w
  }

  private def makeLine(w: DenseVector[Double], x: DenseVector[Double]): DenseMatrix[Double] = {
    //どうやら点と点を結ぶらしい
    //x_2 = -1のときと,x_2 = 1のときを結べばいい
    val x_2_minus = (w(1) * w(1) - w(0)) / (-1.0)
    val x_2_minus_mat = DenseMatrix(x_2_minus, -1.0)
    val x_2_plus = (w(1) * w(1) - w(0))
    val x_2_plus_mat = DenseMatrix(x_2_plus, 1.0)
    val x_2 = DenseMatrix.vertcat(x_2_minus_mat.t, x_2_plus_mat.t)
    return x_2
  }
}
  
