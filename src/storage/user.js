import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    profile: null,
    status: 'idle'
}

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        loggedin: (state, action) => {
            state.profile = action.payload;
            state.status = 'authenticated';
        },
        logout: (state) => {
            state.profile = null;
            state.status = 'idle';
        }
    }
})

export const { loggedin, logout } = userSlice.actions
export default userSlice.reducer;