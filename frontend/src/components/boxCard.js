import { Fragment } from "react"


const BoxCard = ({ timeTaken, title, generate }) => {
    return (
        <Fragment>
            <span className="text-xl font-bold uppercase underline tracking-wide">{title}</span>
            <div className="flex items-center h-4/5">
                <span className="font-bold text-9xl">{timeTaken}</span>
                <span className="font-semibold text-xl ">ms</span>
            </div>
            <hr className="bg-white w-full" />
            <div className="flex w-full items-center justify-around">
                <button
                    className="w-1/2 rounded-lg py-2 px-4 hover:bg-green-500 uppercase text-xl font-medium tracking-wide"
                    onClick={generate}
                >
                    Generate
                </button>
                <button className="w-1/2 rounded-lg py-2 px-4 hover:bg-red-500 uppercase text-xl font-medium tracking-wide">Delete</button>
            </div>
        </Fragment>
    )
}

export default BoxCard