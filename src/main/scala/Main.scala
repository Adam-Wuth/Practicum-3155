//**TODO: Your code here**
sealed trait Expr

case class ManyExprs(lst: List[Expr]) extends Expr 

case class Plus(e1: Expr, e2: Expr) extends Expr

case class Not(e: Expr) extends Expr

case class Count(e: Expr, expr2: Expr) extends Expr







