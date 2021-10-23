import Head from "next/head"
import classnames from "classnames"
import { useEffect, useState } from "react"
import BoxCard from "../components/boxCard"
import ProfileCard from "../components/profileCard"
import { deleteAdjacencyList, generateAdjacencyList } from "src/actions/adjacencyList"
import { deleteAdjacencyMatrix, generateAdjacencyMatrix } from "src/actions/adjacencyMatrix"
import { getStatus } from "src/actions/status"
import { PuffLoader } from "react-spinners"
import { getRandomFriends } from "src/actions/friend"


export default function Home() {
    const [adjacencyListStatus, setAdjacencyListStatus] = useState(false)
    const [adjacencyMatrixStatus, setAdjacencyMatrixStatus] = useState(false)
    const [isLoading, setIsLoading] = useState(false)
    const [users, setUsers] = useState([])

    const getGraphStatus = async () => {
        try {
            const response = await getStatus()
            setAdjacencyListStatus(response.data.adjacencyListStatus)
            setAdjacencyMatrixStatus(response.data.adjacencyMatrixStatus)
        } catch (error) {
            console.log(error)
        }
    }

    const getRandomTenFriends = async () => {
        try {
            setIsLoading(true)
            const response = await getRandomFriends()
            setUsers(response.data)
            console.log(response)
            setIsLoading(false)
        } catch (error) {
            setIsLoading(false)
            console.log(error)
        }
    }

    useEffect(() => {
        getGraphStatus()
    }, [])

    const adjacencyListStatusStyle = classnames({
        "w-4/5 justify-self-center": true,
        "bg-green-300": adjacencyListStatus,
        "bg-red-400": !adjacencyListStatus,
    })

    const adjacencyMatrixStatusStyle = classnames({
        "w-4/5 justify-self-center": true,
        "bg-green-300": adjacencyMatrixStatus,
        "bg-red-400": !adjacencyMatrixStatus,
    })

    return (
        <div className="flex flex-col min-h-screen py-2 bg-black text-white">
            <Head>
                <title>WeConnect</title>
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <div className="flex border divide-x h-96">
                <div className="flex flex-col w-2/5 p-3 items-center space-y-3">
                    <BoxCard
                        title="Adjacency List"
                        setIsLoading={setIsLoading}
                        generate={generateAdjacencyList}
                        remove={deleteAdjacencyList}
                        setStatus={setAdjacencyListStatus}
                    />
                </div>

                <div className="flex flex-col w-1/4 p-3 items-center">
                    <span className="font-bold uppercase underline text-xl">Menu</span>
                    <div className="w-full grid grid-cols-2 gap-y-3 mt-3 uppercase text-center font-semibold">
                        <span className="pb-2 border-b">Status</span>
                        <span className="pb-2 border-b">Indication</span>
                        <span className="border-r">Adjacency List</span>
                        <span className={adjacencyListStatusStyle} />
                        <span className="border-r">Adjacency Matrix</span>
                        <span className={adjacencyMatrixStatusStyle} />
                    </div>
                    <div className="flex w-full h-full items-center justify-center">
                        {
                            isLoading
                                ? <PuffLoader color={"white"} size={175} margin={2} />
                                : null
                        }
                    </div>
                    <button
                        className="w-full rounded-lg py-2 px-4 hover:bg-yellow-400 uppercase text-xl font-medium tracking-wide"
                        onClick={getRandomTenFriends}
                    >
                        Find Random Friends
                    </button>
                </div>

                <div className="flex flex-col w-2/5 p-3 items-center space-y-3">
                    <BoxCard
                        title="Adjacency Matrix"
                        setIsLoading={setIsLoading}
                        generate={generateAdjacencyMatrix}
                        remove={deleteAdjacencyMatrix}
                        setStatus={setAdjacencyMatrixStatus}
                    />
                </div>
            </div>
            <div className="flex h-1/3 overflow-auto">
                {
                    users && users.length !== 0
                        ? users.map(user => {
                            console.log(user)
                            return <ProfileCard
                                key={user.user_id}
                                name={user.name}
                                fans={user.fans}
                                // friends=
                                funny={user.funny}
                                cool={user.cool}
                                star={user.average_stars}
                                useful={user.useful}
                                joined={user.yelping_since}
                            />
                        })

                        : null
                }
            </div>
        </div>
    )
}