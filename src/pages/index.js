import { useEffect, useState } from "react"
import Head from "next/head"
import ProfileCard from "../components/profileCard"
import AdjacencyList from "src/models/adjacencyList"


export default function Home() {
    const [adjacencyList, setAdjacencyList] = useState()
    const [firstTenAdjacencyList, setFirstTenAdjacencyList] = useState([])

    useEffect(() => {
        const data = new AdjacencyList()
        const timeTaken = data.createAdjacencyList()
        setAdjacencyList(data)
        console.log(timeTaken)
    }, [])

    const getFirstTenAdjacencyList = () => {
        const array = Array.from(adjacencyList.getAdjacencyList()).slice(0, 10)
        setFirstTenAdjacencyList(array)
    }

    return (
        <div className="flex flex-col min-h-screen py-2 bg-black text-white">
            <Head>
                <title>WeConnect</title>
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <div className="flex h-96 border divide-x">
                <div className="flex w-1/3 p-3">Box for Adjacency List</div>
                <div className="flex flex-col w-1/3 p-3">
                    <div>Box for Menu Buttons</div>

                </div>
                <div className="flex flex-col w-1/3 p-3 items-center space-y-3">
                    <div>
                        Box for Adjacency Matrix
                    </div>
                    <button className="max-w-max flex border rounded-lg p-2 hover:bg-red-500" onClick={getFirstTenAdjacencyList}>Print First Ten Data</button>
                    <div className="w-full h-1/2 border-t">
                        {
                            firstTenAdjacencyList
                                ? firstTenAdjacencyList.map(data => {
                                    const fromUser = data[0]
                                    const toUser = data[1]
                                    return (
                                        <div>
                                            {fromUser} =&gt; {toUser}
                                        </div>
                                    )
                                })
                                : null
                        }
                    </div>
                </div>
            </div>

            <div className="flex h-1/3 overflow-auto">
                <ProfileCard />
            </div>
        </div>
    )
}
