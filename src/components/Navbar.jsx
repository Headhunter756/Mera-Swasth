import { useSelector } from "react-redux"
import { NavLink } from "react-router-dom"
import '../styles/Navbar.css'
import logo from '../assets/logo.jpeg'

const Navbar = () => {

    const user = useSelector((state) => state.user.profile)
    const navigation = [
        { name: 'Dashboard', href: '#', current: true },
        { name: 'Team', href: '#', current: false },
        { name: 'Projects', href: '#', current: false },
        { name: 'Calendar', href: '#', current: false },
    ]


    return (
        <nav>
            <h2 className="logo">
                <NavLink to={"/"}><img src={logo} alt="logo" className="logo_img" /></NavLink>
            </h2>
            <ul>
                {user &&
                    <>
                        <li><NavLink to={"/dashboard"}>Dashboard</NavLink></li>
                        <li><NavLink to={"/premuim"}>Go Premium</NavLink></li>
                        <li><NavLink to={"/medicine"}>Medicines</NavLink></li>
                        <li><NavLink to={"/history"}>Medical<br />History</NavLink></li>
                    </>
                }
                <li className="emergancy"><NavLink to={"/emergency"}>Emergency</NavLink></li>
            </ul>
            <h2>
                {
                    !user ?
                        <NavLink to={"/login"} className="login_button">Login/Register</NavLink> :
                        <NavLink to={"/profile"} className={"profile_button"}>user.pic</NavLink>
                }
            </h2>
        </nav>
    )

    // return (
    //     <>
    //         <Disclosure
    //             as="nav"
    //             className="relative bg-gray-800/50 after:pointer-events-none after:absolute after:inset-x-0 after:bottom-0 after:h-px after:bg-white/10"
    //         >
    //             <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
    //                 <div className="relative flex h-16 items-center justify-between">
    //                     <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
    //                         {/* Mobile menu button*/}
    //                         <DisclosureButton className="group relative inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-white/5 hover:text-white focus:outline-2 focus:-outline-offset-1 focus:outline-indigo-500">
    //                             <span className="absolute -inset-0.5" />
    //                             <span className="sr-only">Open main menu</span>
    //                             {/* <Bars3Icon aria-hidden="true" className="block size-6 group-data-open:hidden" /> */}
    //                             {/* <XMarkIcon aria-hidden="true" className="hidden size-6 group-data-open:block" /> */}
    //                         </DisclosureButton>
    //                     </div>
    //                     <div className="hidden sm:ml-6 sm:block">
    //                         <div className="flex space-x-4">
    //                             {navigation.map((item) => (
    //                                 <NavLink
    //                                     key={item.name}
    //                                     to={item.href}
    //                                     // aria-current={item.current ? 'page' : undefined}
    //                                     className={({isActive})=>{
    //                                         isActive ? 'bg-gray-950/50 text-white' : 'text-gray-300 hover:bg-white/5 hover:text-white',
    //                                         'rounded-md px-3 py-2 text-sm font-medium'
    //                                     }}
    //                                 >
    //                                     {item.name}
    //                                 </NavLink>
    //                             ))}
    //                             <NavLink to={"/emergency"}>Emergency</NavLink>
    //                         </div>
    //                     </div>
    //                 </div>
    //             </div>
    //         </Disclosure >
    //     </>
    // )

}

export default Navbar