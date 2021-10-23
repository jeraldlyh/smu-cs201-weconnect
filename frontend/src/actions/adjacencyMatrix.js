import axiosInstance from "src/axios"

export const generateAdjacencyMatrix = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.get("/adjacency-matrix")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}

export const deleteAdjacencyMatrix = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.delete("/adjacency-matrix")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}

