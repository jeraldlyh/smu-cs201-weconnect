import axiosInstance from "src/axios"

export const getRandomFriends = () => {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await axiosInstance.get("/random")
            resolve(response)
        } catch (error) {
            reject(error)
        }
    })
}