import axiosInstance from "src/axios"

export const generateAdjacencyMap = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.get("/adjacency-map")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}

export const deleteAdjacencyMap = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.delete("/adjacency-map")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}

