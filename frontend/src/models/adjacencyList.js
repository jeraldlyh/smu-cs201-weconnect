import data from "./data.json"
import moment from "moment"
import nodes from "./node"


export default class AdjacencyMap {
    constructor() {
        this.adjacencyMap = new Map()
    }

    _addNode = (_adjacencyMap, user) => {
        this.adjacencyMap.set(user, [])
    }
    
    // Bidirectional edge
    _addEdge = (_adjacencyMap, fromUser, toUser) => {
        this.adjacencyMap.get(fromUser).push(toUser)
        this.adjacencyMap.get(toUser).push(fromUser)
    }
    
    createAdjacencyMap = () => {
        const startTime = moment(new Date())    // Start timer
    
        nodes.forEach(node => this._addNode(this.adjacencyMap, node))     // Adding nodes into adjacencyMap
    
        for (var key in data) {
            const userData = data[key]
            const fromUser = userData["user_id"]
    
            // Friends data are combined with a comma
            const userFriends = userData["friends"].split(",")
            userFriends.forEach(friend => this._addEdge(this.adjacencyMap, fromUser, friend))
        }

        const endTime = moment(new Date())      // End timer
        console.log(this.adjacencyMap.size)
        return moment.duration(endTime.diff(startTime))
    }

    getAdjacencyMap = () => {
        return this.adjacencyMap
    }
}