import { FaUserFriends, FaRegHandPeace } from "react-icons/fa"
import { BiWinkSmile } from "react-icons/bi"
import { AiOutlineStar } from "react-icons/ai"
import { TiTickOutline } from "react-icons/ti"

const ProfileCard = ({ name, fans, funny, cool, star, useful, joined }) => {
    return (
        <div className="flex flex-col w-56 h-56 border-2 border-white rounded-3xl overflow-hidden">
            {/* Profile Image */}
            {/* <img className="flex object-cover" src="https://picsum.photos/200/" /> */}

            {/* Profile Details */}
            <div className="flex flex-col p-5 place-content-between h-full">
                <div className="uppercase font-bold">
                    <div className="flex items-center">
                        <span className="text-xs">Name:&nbsp;</span>
                        <span>{name ? name : "Unknown"}</span>
                    </div>
                    <div className="flex items-center">
                        <span className="text-xs">Joined at:&nbsp;</span>
                        <span>{joined ? joined : "Unknown"}</span>
                    </div>
                </div>
                <div className="flex w-full justify-between">
                    {/* Fans Attribute */}
                    <div className="flex flex-col items-center">
                        <FaUserFriends />
                        {fans ? fans : 0}
                    </div>
                    {/* Funny Attribute */}
                    <div className="flex flex-col items-center">
                        <BiWinkSmile />
                        {funny ? funny : 0}
                    </div>
                    {/* Cool Attribute */}
                    <div className="flex flex-col items-center">
                        <FaRegHandPeace />
                        {cool ? cool : 0}
                    </div>
                    {/* Average Star Attribute */}
                    <div className="flex flex-col items-center">
                        <AiOutlineStar />
                        {star ? star : 0}
                    </div>
                    {/* Useful Attribute */}
                    <div className="flex flex-col items-center">
                        <TiTickOutline />
                        {useful ? useful : 0}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ProfileCard