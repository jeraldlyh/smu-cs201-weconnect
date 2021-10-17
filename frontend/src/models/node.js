import data from "./data.json"
import User from "./user"


const nodes = new Set()

for (var key in data) {
    const userData = data[key]
    const userID = userData["user_id"]

    const userWithoutData = new User(userID)
    const friends = userData["friends"].split(",")      // Friends data are combined with a comma

    // has() method takes O(1) time complexity
    // Checks if user was previously a dummy node that was added
    // Update the node with actual user attributes
    if (nodes.has(userWithoutData)) {
        nodes.delete(userWithoutData)
        const userWithData = new User(
            userID,
            userData["name"],
            userData["review_count"],
            userData["yelping_since"],
            userData["useful"],
            userData["funny"],
            userData["cool"],
            friends,
            userData["fans"],
            userData["average_stars"]
        )
        nodes.add(userWithData)
    }
    // Add dummy nodes for user's friends
    friends.forEach(friendID => nodes.add(new User(friendID)))
}

export default nodes