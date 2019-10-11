/*
 * Copyright 2019 complate.org
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
function render(view, params, stream) {
    if (view === 'StaticView') {
        stream.write('Render view: ' + view);
    } else if (view === 'ModelView') {
        stream.write('View ' + view + ' with params.title=' + params.title)
    } else if (view === 'BindingView') {
        stream.write('View ' + view + ' with functionBinding.greet=' + functionBinding.greet(constantBinding));
    } else {
        stream.write("View not found: " + view);
    }
}
