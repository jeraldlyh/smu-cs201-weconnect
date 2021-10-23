import { FaUserFriends, FaRegHandPeace } from "react-icons/fa"
import { BiWinkSmile } from "react-icons/bi"
import { AiOutlineStar } from "react-icons/ai"
import { TiTickOutline } from "react-icons/ti"

const ProfileCard = ({ name, fans, funny, cool, star, useful, joined, addFriendList, addFriendMatrix }) => {
    return (
        <div className="flex flex-col h-56 w-1/5 border-2 border-white rounded-3xl overflow-hidden">
            {/* Profile Image */}
            {/* <img className="flex object-cover" src="https://picsum.photos/200/" /> */}

            {/* Profile Details */}
            <div className="flex flex-col p-5 place-content-between h-full">
                <div className="grid grid-cols-2 uppercase font-bold">
                    <span className="text-xs">Name:&nbsp;</span>
                    <span>{name ? name : "Unknown"}</span>
                    <span className="text-xs">Joined at:&nbsp;</span>
                    <span>{joined ? joined : "Unknown"}</span>
                </div>
                <div className="flex w-full justify-between">
                    {/* Fans Attribute */}
                    <div className="flex flex-col items-center font-semibold">
                        <FaUserFriends />
                        {fans ? fans : 0}
                    </div>
                    {/* Funny Attribute */}
                    <div className="flex flex-col items-center font-semibold">
                        <BiWinkSmile />
                        {funny ? funny : 0}
                    </div>
                    {/* Cool Attribute */}
                    <div className="flex flex-col items-center font-semibold">
                        <FaRegHandPeace />
                        {cool ? cool : 0}
                    </div>
                    {/* Average Star Attribute */}
                    <div className="flex flex-col items-center font-semibold">
                        <AiOutlineStar />
                        {star ? star : 0}
                    </div>
                    {/* Useful Attribute */}
                    <div className="flex flex-col items-center font-semibold">
                        <TiTickOutline />
                        {useful ? useful : 0}
                    </div>
                </div>
                <div className="flex items-center justify-between space-x-1">
                    <button className="rounded-lg w-1/2 py-2 px-4 hover:bg-blue-400 uppercase font-medium" onClick={addFriendList}>
                        Add Friend (List)
                    </button>
                    <button className="rounded-lg w-1/2 py-2 px-4 hover:bg-purple-400 uppercase font-medium" onClick={addFriendMatrix}>
                        Add Friend (Matrix)
                    </button>
                </div>
            </div>
        </div>
    )
}

export default ProfileCard