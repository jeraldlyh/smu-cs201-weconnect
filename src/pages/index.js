import { useEffect } from "react"
import Head from "next/head"
import ProfileCard from "../components/profileCard"
import { loadData } from "../database"


export default function Home() {
    useEffect(() => {
        loadData();
    }, [])
    return (
        <div className="flex flex-col min-h-screen py-2 bg-black text-white">
            <Head>
                <title>WeConnect</title>
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <div className="flex h-96 border divide-x">
                <div className="flex w-1/3 p-3">Box for Adjacency List</div>
                <div className="flex w-1/3 p-3">Box for Menu Buttons</div>
                <div className="flex w-1/3 p-3">Box for Adjacency Matrix</div>
            </div>

            <div className="flex h-1/3 overflow-auto">
                <ProfileCard />
            </div>
        </div>
    )
}
