import Head from "next/head"
import { useEffect, useState, Fragment } from "react"
import AdjacencyList from "../components/adjacencyList"
import ProfileCard from "../components/profileCard"


export default function Home() {
    return (
        <div className="flex flex-col min-h-screen py-2 bg-black text-white">
            <Head>
                <title>WeConnect</title>
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <div className="flex border divide-x">
                <div className="flex w-1/3 p-3">Box for Adjacency Matrix</div>
                <div className="flex flex-col w-1/3 p-3">
                    <div>Box for Menu Buttons</div>
                </div>

                <div className="flex flex-col w-1/3 p-3 items-center space-y-3">
                    <AdjacencyList />
                </div>

            </div>
            <div className="flex h-1/3 overflow-auto">
                <ProfileCard />
            </div>
        </div>
    )
}
