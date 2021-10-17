import { Fragment, useEffect, useState } from "react"
import AdjacencyListModel from "../models/adjacencyList"


const AdjacencyList = () => {
    const [adjacencyList, setAdjacencyList] = useState()
    const [firstTenAdjacencyList, setFirstTenAdjacencyList] = useState([])

    useEffect(() => {
        const data = new AdjacencyListModel()
        const timeTaken = data.createAdjacencyList()
        setAdjacencyList(data)
        console.log(timeTaken)
    }, [])

    const getFirstTenAdjacencyList = () => {
        const array = Array.from(adjacencyList.getAdjacencyList()).slice(0, 10)
        setFirstTenAdjacencyList(array)
    }

    return (
        <Fragment>
            <span>Box for Adjacency List</span>
            <button className="max-w-max flex border rounded-lg p-2 hover:bg-red-500" onClick={getFirstTenAdjacencyList}>Generate Adjacency List</button>
            <hr className="bg-white w-full" />
            {
                firstTenAdjacencyList && firstTenAdjacencyList.length !== 0
                    ? <div className="w-full grid grid-cols-2 justify-items-center mt-3">
                        <span className="font-semibold">User ID</span>
                        <span className="font-semibold">Number of Friends</span>

                        {
                            firstTenAdjacencyList.map(data => {
                                const fromUser = data[0]
                                const numOfFriends = data[1].length
                                return (
                                    <Fragment>
                                        <span>{fromUser}</span>
                                        <span>{numOfFriends}</span>
                                    </Fragment>
                                )
                            })
                        }
                    </div>
                    : <div>No data is found</div>
            }
        </Fragment>
    )
}

export default AdjacencyList