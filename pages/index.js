import ProfileCard from 'components/profileCard'
import Head from 'next/head'

export default function Home() {
    return (
        <div className="flex flex-col items-center justify-center min-h-screen py-2 bg-black text-white">
            <Head>
                <title>WeConnect</title>
                <link rel="icon" href="/favicon.ico" />
            </Head>

            <div className="flex">
                <ProfileCard />
            </div>
        </div>
    )
}
