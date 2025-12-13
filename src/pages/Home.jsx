import Card from "../components/Card"
import secure_img from '../assets/security.png'
import analysis_img from '../assets/wearable.jpeg'
import medicine_img from '../assets/inventory.jpeg'
import schedule_img from '../assets/schedule.jpeg'
import bot_img from '../assets/bot.jpeg'
import '../styles/Home.css'

const Home = () => {
    return (
        <div className='homepage'>
            <div className="features">
                <h2>Why choose Mera Swasth ?</h2>
                <div className="feature">
                    <Card src={secure_img} alt="secured" desc="End-to-end encrypted health data" />
                    <Card src={medicine_img} alt="inventory" desc="Comprehensive medicine inventory management" />
                    <Card src={schedule_img} alt="scheduling" desc="Easy scheduling of doctor appointments" />
                    <Card src={analysis_img} alt="analysis" desc=" Personalized health insights from wearable devices" />
                    <Card src={bot_img} alt="assitance" desc="AI-powered assistance for reports and trends" />
                </div>
            </div>
            <div className="aboutme">
                <h2>About us</h2>
                Mera Swasth is your trusted digital health companion. We securely store your medical history, manage your medicine inventory, and make it easy to schedule appointments with doctors. Our platform helps you stay on top of your health by showing recent trends and insights, while seamlessly collecting data from your smartwatch — including blood pressure, sugar levels, and more. With interactive graphs and clear visualizations, Mera Swasth empowers you to understand your health better and take informed steps toward wellness.
            </div>
        </div>
    )
}

export default Home