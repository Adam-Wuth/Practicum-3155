//**TODO: Your code here**
//Extends expr
sealed trait Expr

sealed trait Value extends Expr

case class ManyExprs(lst: List[Expr]) extends Expr 

case class Plus(e1: Expr, e2: Expr) extends Expr

case class Not(e: Expr) extends Expr

case class Count(e1: Expr, e2: Expr) extends Expr

//Extends value

case object Cry extends Value

case object Happy extends Value

case object VeryHappy extends Value

case object Sleepy extends Value

case object Stun extends Value

case class ManyVals(v : List[Value])








