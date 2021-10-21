import axiosInstance from "src/axios"

export const generateAdjacencyList = () => {
    axiosInstance
        .get("/adjacency-list")
        .then(response => console.log(response))
        .catch(error => console.log(error))
}

