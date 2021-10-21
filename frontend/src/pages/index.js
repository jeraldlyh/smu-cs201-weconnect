import Head from "next/head"
import classnames from "classnames"
import { useEffect, useState } from "react"
import BoxCard from "../components/boxCard"
import ProfileCard from "../components/profileCard"


export default function Home() {
    const [adjacencyListTimeTaken, setAdjacencyListTimeTaken] = useState(0)
    const [adjacencyMatrixTimeTaken, setAdjacencyMatrixTimeTaken] = useState(0)
    const [adjacencyListStatus, setAdjacencyListStatus] = useState(false)
    const [adjacencyMatrixStatus, setAdjacencyMatrixStatus] = useState(false)

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
                    <BoxCard timeTaken={adjacencyListTimeTaken} title="Adjacency List" />
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
                </div>

                <div className="flex flex-col w-2/5 p-3 items-center space-y-3">
                    <BoxCard timeTaken={adjacencyMatrixTimeTaken} title="Adjacency Matrix" />
                </div>
            </div>
            <div className="flex h-1/3 overflow-auto">
                <ProfileCard />
            </div>
        </div>
    )
}
