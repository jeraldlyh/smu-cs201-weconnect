import axiosInstance from "src/axios"

export const generateAdjacencySet = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.get("/adjacency-set")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}

export const deleteAdjacencySet = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.delete("/adjacency-set")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}

