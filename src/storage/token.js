import { createSlice } from "@reduxjs/toolkit"

const initialState = {
    token: "",
    status: "idle"
}

const tokenSlice = createSlice({
    name: "token",
    initialState,
    reducers: {
        save: (state, action) => {
            state.token = action.payload,
                state.status = "loggedin"
        },
        remove: (state) => {
            state.token = "",
                state.status = "idle"
        }
    }
})

export const { save, remove } = tokenSlice.actions
export default tokenSlice.reducer