/*
 * Copyright 2024 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.xrfundamentals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.xr.compose.spatial.EdgeOffset
import androidx.xr.compose.spatial.Orbiter
import androidx.xr.compose.spatial.OrbiterEdge
import androidx.xr.compose.spatial.Subspace
import androidx.xr.compose.subspace.SpatialCurvedRow
import androidx.xr.compose.subspace.SpatialPanel
import androidx.xr.compose.subspace.layout.SpatialRoundedCornerShape
import androidx.xr.compose.subspace.layout.SubspaceModifier
import androidx.xr.compose.subspace.layout.height
import androidx.xr.compose.subspace.layout.movable
import androidx.xr.compose.subspace.layout.width
import com.example.android.xrfundamentals.ui.component.Note
import com.example.android.xrfundamentals.ui.component.NoteList
import com.example.android.xrfundamentals.ui.component.ToggleSpaceModeButton
import com.example.android.xrfundamentals.ui.component.XRFundamentalsTopAppBar
import com.example.android.xrfundamentals.ui.layout.CompactLayout
import com.example.android.xrfundamentals.ui.layout.ExpandedLayout

@Composable
fun XRFundamentalsApp(
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    Scaffold(
        topBar = { XRFundamentalsTopAppBar() }
    ) { innerPadding ->

        val modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()

        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
            CompactLayout(
                modifier = modifier,
                primaryContent = {
                    NoteList()
                }
            )
        } else {
            ExpandedLayout(
                modifier = modifier,
                primaryContent = {
                    NoteList(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    )
                }
            )
        }
    }
    Subspace {
        SpatialCurvedRow(
            curveRadius = 825.dp
        ) {
            Orbiter(
                position = OrbiterEdge.Top,
                alignment = Alignment.End,
                offset = EdgeOffset.inner(16.dp),
                shape = SpatialRoundedCornerShape(
                    CornerSize(percent = 100)
                )
            ) {
                ToggleSpaceModeButton()
            }
            for (index in 1..3) {
                SpatialPanel(
                    modifier = SubspaceModifier
                        .width(200.dp)
                        .height(100.dp)
                        .movable(true, false, false)

                ) {
                    Note("Note $index")
                }
            }
        }
    }
}