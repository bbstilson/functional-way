package greedy
import scala.collection.immutable.TreeMap

/*
This is the classic knapsack problem which allows fractional weights.
Problem: Given a Knapsack of Capacity W, weights w1, w2...wi with values v1, v2...vi,
find the maximum value that the knapsack can contain.
 */

object FractionalKnapsack extends App {
    type ValuePerWeight = Float
    type OptimalSolution = Float
    type Weight = Int
  def maxValue(itemWeights: Array[Int], itemValues: Array[Int], knapsackCapacity: Int): OptimalSolution = {
    val array: Array[(ValuePerWeight, Weight)] = itemValues.zip(itemWeights).map {
        case (vi, wi) => ((vi.toFloat/wi),wi)
    }
    
    def recurse(map: TreeMap[ValuePerWeight, Weight], currentCapacity: Weight): OptimalSolution =  
        if(map.isEmpty) 0
        else {
            val (valuePerWeight, weight) = map.head
            if(weight <= currentCapacity) weight * valuePerWeight + recurse(map.tail, currentCapacity - weight)
            else currentCapacity * valuePerWeight
        }
        
    val map: TreeMap[ValuePerWeight, Weight] = TreeMap(array:_*)(implicitly[Ordering[Float]].reverse)
    recurse(map = map, currentCapacity = knapsackCapacity)
  }
}
