import data from "./data.json"
import User from "./user"


const nodes = new Set()

for (var key in data) {
    const userData = data[key]
    const fromUser = userData["user_id"]

    // Friends data are combined with a comma
    const userFriends = userData["friends"].split(",")
    userFriends.forEach(friend => nodes.add(friend))
    nodes.add(fromUser)
}

export default nodes