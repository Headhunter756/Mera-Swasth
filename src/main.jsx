import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Dashboard from './pages/Dashboard.jsx'
import Home from './pages/Home.jsx'
import { Provider } from 'react-redux'
import store from "./storage/store.js"
import Signup from './pages/Signup.jsx'
import Login from './pages/Login.jsx'
import Register from './pages/Register.jsx'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        index: true,
        element: <Home />
      },
      {
        element: <Signup />,
        children:[
          {
            path:"login",
            element:<Login/>
          },
          {
            path:"register",
            element:<Register/>
          }

        ]
      },
      {
        path: 'dashboard',
        element: <Dashboard/>
      }
    ]
  }
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </StrictMode>,
)
