export default class User {
    constructor(id, name, reviewCount, dateJoined, useful, funny, cool, friends, fans, stars) {
        this.id = id
        this.name = name
        this.reviewCount = reviewCount
        this.dateJoined = dateJoined
        this.useful = useful
        this.funny = funny
        this.cool = cool
        this.friends = friends
        this.fans = fans
        this.stars = stars
    }

    // Overloaded constructor for users that only exist in friend list but does
    // not contain data for attributes
    constructor(id) {
        this.id = id
    }

    // Method for user that was previously added as an empty node
    updateAttributes = (name, reviewCount, dateJoined, useful, funny, cool, friends, fans, stars) => {
        this.name = name
        this.reviewCount = reviewCount
        this.dateJoined = dateJoined
        this.useful = useful
        this.funny = funny
        this.cool = cool
        this.friends = friends
        this.fans = fans
        this.stars = stars
    }
}