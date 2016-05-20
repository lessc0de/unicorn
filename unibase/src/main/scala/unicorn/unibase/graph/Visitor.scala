/*******************************************************************************
 * (C) Copyright 2015 ADP, LLC.
 *   
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package unicorn.unibase.graph

import unicorn.json._

object VertexColor extends Enumeration {
  type VertexColor = Value

  /** White marks vertices that have yet to be discovered, */
  val White = Value

  /** Gray marks a vertex that is discovered but still
    * has vertices adjacent to it that are undiscovered. */
  val Gray = Value

  /** A black vertex is discovered vertex that is not
    * adjacent to any white vertices.
    */
  val Black = Value
}

/** Graph traversal visitor.
  *
  * @author Haifeng Li
  */
trait Visitor {
  /** Returns the vertex of given ID. */
  def v(vertex: Long): Vertex

  /** Visit a vertex during graph traversal.
    *
    * @param vertex the vertex on visiting.
    * @param edge the incoming arc (None for starting vertex).
    * @param hops the number of hops from the starting vertex to this vertex.
    */
  def visit(vertex: Vertex, edge: Option[Edge], hops: Int): Unit

  /** Returns an iterator of the neighbors and associated edges of a vertex.
    *
    * @param vertex the vertex on visiting.
    * @param hops the number of hops from starting vertex, which may be used for early termination.
    * @return an iterator of the outgoing edges
    */
  def neighbors(vertex: Vertex, hops: Int): Iterator[(Long, Edge)]

  /** The weight of edge (e.g. shortest path search). */
  def weight(edge: Edge): Double = edge.properties match {
    case JsInt(x) => x
    case JsCounter(x) => x
    case JsLong(x) => x
    case _ => 1.0
  }
}