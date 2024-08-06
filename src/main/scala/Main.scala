//**TODO: Your code here**
// Extends expr
sealed trait Expr {
  def eval: Value = {
    this match {
      case v: Value => v
      case ManyExprs(lst) => ManyVals(lst.map(_.eval))
      case Plus(e1, e2) => 
        val v1 = e1.eval
        val v2 = e2.eval
        (v1, v2) match{
            case (VeryHappy, _) => VeryHappy
            case (_, VeryHappy) => VeryHappy
            case (Cry, _) => v2
            case(Happy, Cry) => Cry
            case(Stun, Cry) => Cry
            
            // case (_, Cry) =>
            //     v1 match{
            //         case Happy => Cry
            //         case Stun => Cry
            //     }
            case _ => throw new RuntimeException("Error Evaluating in Plus")
        }
        
      case _ => throw new RuntimeException("Error Evaluating")
    }
  }
}

sealed trait Value extends Expr

case class ManyExprs(lst: List[Expr]) extends Expr

case class Plus(e1: Expr, e2: Expr) extends Expr

case class Not(e: Expr) extends Expr

case class Count(e1: Expr, e2: Expr) extends Expr

// Extends value

case object Cry extends Value

case object Happy extends Value

case object VeryHappy extends Value

case object Sleepy extends Value

case object Stun extends Value

case class ManyVals(v: List[Value]) extends Value
