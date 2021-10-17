import data from "./data.json"
import moment from "moment"
import nodes from "./node"


export default class AdjacencyList {
    constructor() {
        this.adjacencyList = new Map()
    }

    _addNode = (_adjacencyList, user) => {
        this.adjacencyList.set(user, [])
    }
    
    // Bidirectional edge
    _addEdge = (_adjacencyList, fromUser, toUser) => {
        this.adjacencyList.get(fromUser).push(toUser)
        this.adjacencyList.get(toUser).push(fromUser)
    }
    
    createAdjacencyList = () => {
        const startTime = moment(new Date())    // Start timer
    
        nodes.forEach(node => this._addNode(this.adjacencyList, node))     // Adding nodes into adjacencyList
    
        for (var key in data) {
            const userData = data[key]
            const fromUser = userData["user_id"]
    
            // Friends data are combined with a comma
            const userFriends = userData["friends"].split(",")
            userFriends.forEach(friend => this._addEdge(this.adjacencyList, fromUser, friend))
        }

        const endTime = moment(new Date())      // End timer
        console.log(this.adjacencyList.size)
        return moment.duration(endTime.diff(startTime))
    }

    getAdjacencyList = () => {
        return this.adjacencyList
    }
}

export const loadData = () => {
    var a = 1
    for (var i in data) {
        console.log(data[i])
        a += 1
        if (a == 5) {
            break
        }
    }
}
