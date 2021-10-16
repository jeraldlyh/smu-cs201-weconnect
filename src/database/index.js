import data from "./result.json"

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