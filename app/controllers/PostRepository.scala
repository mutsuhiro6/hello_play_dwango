package controllers

object PostRepository {

  var posts: Seq[Post] = Seq.empty[Post]

  def findAll: Seq[Post] = posts

  def add(post: Post): Unit = {
    posts = posts :+ post
  }
}
