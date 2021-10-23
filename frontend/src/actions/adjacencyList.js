import axiosInstance from "src/axios"

export const generateAdjacencyList = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.get("/adjacency-list")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}

export const deleteAdjacencyList = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.delete("/adjacency-list")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}

