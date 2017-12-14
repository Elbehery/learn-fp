package learnfp.traversable

import learnfp.applicative.Applicative
import learnfp.functor.Functor

class TraversableOps[A, F[_]](initialXs:List[F[A]])(implicit functor: Functor[F]) {
  import learnfp.functor.FunctorOps._
  import learnfp.applicative.ListInstance._
  import learnfp.applicative.ApplicativeOps._

  def traverse[B](fx:A => B)(implicit applicative: Applicative[List[B], List[B], F]):F[List[B]] = {
    def continue(xs:List[F[A]]):F[List[B]] = xs match {
      case Nil => applicative.pure(Nil)
      case (h::ts) => toApplicativeOps({ (h:B, t:List[B]) => (h :: t) } .curried `<$>` (h fmap fx)) <*> continue(ts)
    }
    continue(initialXs)
  }

  def sequence(implicit applicative: Applicative[List[A], List[A], F]):F[List[A]] = {
    traverse(identity)
  }
}

object TraversableOps {
  implicit def toTraversableOps[A, F[_]](xs:List[F[A]])(implicit functor:Functor[F]) = new TraversableOps[A, F](xs)
}
