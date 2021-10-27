import Head from "next/head"
import classnames from "classnames"
import { useEffect, useState } from "react"
import BoxCard from "../components/boxCard"
import ProfileCard from "../components/profileCard"
import { deleteAdjacencyList, generateAdjacencyList } from "src/actions/adjacencyList"
import { deleteAdjacencyMatrix, generateAdjacencyMatrix } from "src/actions/adjacencyMatrix"
import { deleteAdjacencySet, generateAdjacencySet } from "src/actions/adjacencySet"
import { getStatus } from "src/actions/status"
import { PuffLoader } from "react-spinners"
import { addFriends, getRandomFriends } from "src/actions/friend"


export default function Home() {
    const [adjacencyListStatus, setAdjacencyListStatus] = useState(false)
    const [adjacencyMatrixStatus, setAdjacencyMatrixStatus] = useState(false)
    const [adjacencySetStatus, setAdjacencySetStatus] = useState(false)
    const [isLoading, setIsLoading] = useState(false)
    const [users, setUsers] = useState([])
    const [timeTakenAdjacencyList, setTimeTakenAdjacencyList] = useState(0)
    const [timeTakenAdjacencyMatrix, setTimeTakenAdjacencyMatrix] = useState(0)
    const [timeTakenAdjacencySet, setTimeTakenAdjacencySet] = useState(0)

    // Hardcode user as Gab
    const LOGGED_USER = process.env.NODE_ENV === "development" ? "apple" : "dIIKEfOgo0KqUfGQvGikPg"

    useEffect(() => {
        getGraphStatus()
    }, [])

    const getGraphStatus = async () => {
        try {
            const response = await getStatus()
            setAdjacencyListStatus(response.data.adjacencyListStatus)
            setAdjacencyMatrixStatus(response.data.adjacencyMatrixStatus)
            //TODO
            // setAdjacencySetStatus(response.data.adjacencySetStatus)
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

    const addFriend = async (toUser, graphType) => {
        try {
            // Check if environment is in testing or production mode and set username according to the dataset used
            setIsLoading(true)
            const response = await addFriends(LOGGED_USER, toUser, graphType)
            setUsers(response.data.friendSuggestions)

            if (graphType === "list") {
                setAdjacencyListStatus(true)
                setTimeTakenAdjacencyList(response.data.timeTaken)
            } else if (graphType === "matrix") {
                setAdjacencyMatrixStatus(true)
                setTimeTakenAdjacencyMatrix(response.data.timeTaken)
            } else {
                setAdjacencySetStatus(true)
                setTimeTakenAdjacencySet(response.data.timeTaken)
            }

            console.log(response)
            setIsLoading(false)
        } catch (error) {
            setIsLoading(false)
            console.log(error)
        }
    }

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

    const adjacencySetStatusStyle = classnames({
        "w-4/5 justify-self-center": true,
        "bg-green-300": adjacencySetStatus,
        "bg-red-400": !adjacencySetStatus,
    })

    return (
        <div className="flex flex-col min-h-screen py-2 bg-black text-white">
            <Head>
                <title>WeConnect</title>
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <div className="flex border divide-x h-96">
                <div className="flex flex-col w-1/3 p-3 items-center space-y-3">
                    <BoxCard
                        title="Adjacency List"
                        setIsLoading={setIsLoading}
                        generate={generateAdjacencyList}
                        remove={deleteAdjacencyList}
                        setStatus={setAdjacencyListStatus}
                        timeTaken={timeTakenAdjacencyList}
                        setTimeTaken={setTimeTakenAdjacencyList}
                    />
                </div>

                <div className="flex flex-col w-1/3 p-3 items-center space-y-3">
                    <BoxCard
                        title="Adjacency Matrix"
                        setIsLoading={setIsLoading}
                        generate={generateAdjacencyMatrix}
                        remove={deleteAdjacencyMatrix}
                        setStatus={setAdjacencyMatrixStatus}
                        timeTaken={timeTakenAdjacencyMatrix}
                        setTimeTaken={setTimeTakenAdjacencyMatrix}
                    />
                </div>

                <div className="flex flex-col w-1/3 p-3 items-center space-y-3">
                    <BoxCard
                        title="Adjacency Set"
                        setIsLoading={setIsLoading}
                        generate={generateAdjacencySet}
                        remove={deleteAdjacencySet}
                        setStatus={setAdjacencySetStatus}
                        timeTaken={timeTakenAdjacencySet}
                        setTimeTaken={setTimeTakenAdjacencySet}
                    />
                </div>

                <div className="flex flex-col w-1/3 p-3 items-center">
                    <span className="font-bold uppercase underline text-xl">Menu</span>
                    <div className="w-full grid grid-cols-2 gap-y-3 mt-3 uppercase text-center font-semibold">
                        <span className="pb-2 border-b">Status</span>
                        <span className="pb-2 border-b">Indication</span>
                        <span className="border-r min-w-min">Adjacency List</span>
                        <span className={adjacencyListStatusStyle} />
                        <span className="border-r min-w-min">Adjacency Matrix</span>
                        <span className={adjacencyMatrixStatusStyle} />
                        <span className="border-r min-w-min">Adjacency Set</span>
                        <span className={adjacencySetStatusStyle} />
                    </div>
                    <div className="flex w-full h-full items-center justify-center">
                        {
                            isLoading
                                ? <PuffLoader color={"white"} size={150} margin={2} />
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
            </div>
            <div className="flex flex-wrap justify-around mt-2 h-full overflow-y-auto scrollbar-thin scrollbar-thumb-gray-400 scrollbar-track-white scrollbar-thumb-rounded-full scrollbar-track-rounded-full">
                {
                    users && users.length !== 0
                        ? users.map(user => {
                            // const userFriends = user.friends.split(",")

                            // Do not display current user
                            if (!user || user.user_id === LOGGED_USER) {
                                return null
                            }
                            return <ProfileCard
                                key={user.user_id}
                                name={user.name}
                                fans={user.fans}
                                funny={user.funny}
                                cool={user.cool}
                                star={user.average_stars}
                                useful={user.useful}
                                joined={user.yelping_since}
                                addFriendList={() => addFriend(user.user_id, "list")}
                                addFriendMatrix={() => addFriend(user.user_id, "matrix")}
                            />
                        })
                        : null
                }
            </div>
        </div>
    )
}