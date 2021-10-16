import { MdOutlineRateReview } from "react-icons/md"
import { BiWinkSmile } from "react-icons/bi"
import { AiOutlineStar } from "react-icons/ai"
import { TiTickOutline } from "react-icons/ti"

const ProfileCard = () => {
    return (
        <div className="flex w-1/4 border-2 border-white rounded-3xl overflow-hidden">
            {/* Profile Image */}
            <img className="w-1/3 h-full object-cover" src="https://picsum.photos/200/" />

            {/* Profile Details */}
            <div className="flex flex-col p-3 ml-2 w-2/3 space-y-2">
                <div className="uppercase font-bold">Name</div>
                <div className="font-semibold">Description</div>
                <div className="flex w-full justify-between">
                    <div className="flex flex-col items-center">
                        <MdOutlineRateReview />
                        1
                    </div>
                    <div className="flex flex-col items-center">
                        <BiWinkSmile />
                        1
                    </div>
                    <div className="flex flex-col items-center">
                        <MdOutlineRateReview />
                        1
                    </div>
                    <div className="flex flex-col items-center">
                        <AiOutlineStar />
                        1
                    </div>
                    <div className="flex flex-col items-center">
                        <TiTickOutline />
                        1
                    </div>
                </div>
                <div className="text-sm">Joined at:</div>
            </div>
        </div>
    )
}

export default ProfileCard